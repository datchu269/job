package com.example.rabbitmqdemo.mapper;

import com.example.rabbitmqdemo.entity.hi1.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ObjectRowMapper implements RowMapper<ObjectTable> {

//    @Override
//    public ObjectTable mapRow(ResultSet rs, int rowNum) throws SQLException {
//        long objectId = rs.getLong("object_id");
//
//        // Creating a map to store objects by their IDs
//        Map<Long, ObjectTable> objectMap = new HashMap<>();
//
//        // Retrieving object from the map if it already exists
//        ObjectTable objectTable = objectMap.get(objectId);
//
//        // If the object doesn't exist in the map, create a new object
//        if (objectTable == null) {
//            objectTable = ObjectTable.builder()
//                    .id(objectId)
//                    .objectType(rs.getString("object_type"))
//                    .objectIdentify(rs.getString("object_identify"))
//                    .liTaskObjects(new ArrayList<>())
//                    .targets(new ArrayList<>())
//                    .deliveryInfoList(new ArrayList<>())
//                    .build();
//            objectMap.put(objectId, objectTable);
//        }
//
//        // If li_task_object is not null, create a LiTaskObject and add it to the object's list
//        if (rs.getLong("li_task_id") != 0) {
//            LiTaskObject liTaskObject = LiTaskObject.builder()
//                    .id(rs.getLong("li_task_id"))
//                    .status(rs.getInt("li_task_status"))
//                    .build();
//            objectTable.getLiTaskObjects().add(liTaskObject);
//        }
//
//        // If target is not null, create a Target and add it to the object's list
//        if (rs.getLong("target_id") != 0) {
//            Target target = Target.builder()
//                    .id(rs.getLong("target_id"))
//                    .identifierType(rs.getString("target_identifier_type"))
//                    .build();
//            objectTable.getTargets().add(target);
//        }
//
//        // If delivery_info is not null, create a DeliveryInfo and add it to the object's list
//        if (rs.getLong("delivery_info_id") != 0) {
//            long deliveryInfoId = rs.getLong("delivery_info_id");
//            DeliveryInfo deliveryInfo = objectTable.getDeliveryInfoById(deliveryInfoId);
//            if (deliveryInfo == null) {
//                deliveryInfo = DeliveryInfo.builder()
//                        .id(deliveryInfoId)
//                        // Populate other fields as needed
//                        .build();
//                objectTable.addDeliveryInfo(deliveryInfo);
//            }
//
//            // Create a DeliveryAddress and add it to the delivery_info's list of addresses
//            DeliveryAddress deliveryAddress = DeliveryAddress.builder()
//                    .id(rs.getLong("delivery_address_id"))
//                    .port(rs.getInt("port")) // Assuming the field name is 'port'
//                    .build();
//            deliveryInfo.getDeliveryAddresses().add(deliveryAddress);
//        }
//
//        return objectTable;
//    }


    @Override
    public ObjectTable mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ObjectTable objectEntity = ObjectTable.builder()
                .id(resultSet.getLong("object_id"))
                .objectType(resultSet.getString("object_type"))
                .objectIdentify(resultSet.getString("object_identify"))
                .build();

        Map<Long, LiTaskObject> liTaskObjectMap = new HashMap<>();
        Map<Long, Target> targetMap = new HashMap<>();
        Map<Long, DeliveryInfo> deliveryInfoMap = new HashMap<>();
        Map<Long, List<DeliveryAddress>> deliveryAddressMap = new HashMap<>();

        // Lặp qua kết quả truy vấn để xây dựng các đối tượng liên quan
        do {
            Long liTaskObjectId = resultSet.getLong("li_task_object_id");
            if (!resultSet.wasNull()) {
                LiTaskObject liTaskObjectEntity = liTaskObjectMap.get(liTaskObjectId);
                if (liTaskObjectEntity == null) {
                    liTaskObjectEntity = LiTaskObject.builder()
                            .id(liTaskObjectId)
                            .status(resultSet.getInt("status"))
                            .build();
                    liTaskObjectMap.put(liTaskObjectId, liTaskObjectEntity);
                    objectEntity.getLiTaskObjects().add(liTaskObjectEntity);
                }
//                objectEntity.getLiTaskObjects().add(liTaskObjectEntity);
            }

            Long targetId = resultSet.getLong("target_id");
            if (!resultSet.wasNull()) {
                Target targetEntity = targetMap.get(targetId);
                if (targetEntity == null) {
                    targetEntity = Target.builder()
                            .id(targetId)
                            .identifierType(resultSet.getString("identifier_type"))
                            .build();
                    targetMap.put(targetId, targetEntity);
                    objectEntity.getTargets().add(targetEntity);
                }
//                objectEntity.getTargets().add(targetEntity);
            }

            Long deliveryInfoId = resultSet.getLong("delivery_info_id");
            if (!resultSet.wasNull()) {
                DeliveryInfo deliveryInfoEntity = deliveryInfoMap.get(deliveryInfoId);
                if (deliveryInfoEntity == null) {
                    deliveryInfoEntity = DeliveryInfo.builder()
                            .id(deliveryInfoId)
                            .liid(resultSet.getString("liid"))
                            .build();
                    deliveryInfoMap.put(deliveryInfoId, deliveryInfoEntity);
                    objectEntity.getDeliveryInfos().add(deliveryInfoEntity);
                }
//                objectEntity.getDeliveryInfos().add(deliveryInfoEntity);
            }

            Long deliveryAddressId = resultSet.getLong("delivery_address_id");
            if (!resultSet.wasNull()) {
                DeliveryAddress deliveryAddressEntity = DeliveryAddress.builder()
                        .id(deliveryAddressId)
                        .port(resultSet.getInt("port"))
                        .build();
                Long deliveryInfoKey = resultSet.getLong("delivery_info_id");
                List<DeliveryAddress> deliveryAddressList = deliveryAddressMap.get(deliveryInfoKey);
                if (deliveryAddressList == null) {
                    deliveryAddressList = new ArrayList<>();
                    deliveryAddressMap.put(deliveryInfoKey, deliveryAddressList);
                }
                deliveryAddressList.add(deliveryAddressEntity);
            }
        } while (resultSet.next());

        // Set danh sách địa chỉ giao hàng cho mỗi đối tượng delivery info
        for (DeliveryInfo deliveryInfoEntity : objectEntity.getDeliveryInfos()) {
            List<DeliveryAddress> deliveryAddressList = deliveryAddressMap.get(deliveryInfoEntity.getId());
            if (deliveryAddressList != null) {
                deliveryInfoEntity.setDeliveryAddresses(deliveryAddressList);
            }
        }

        return objectEntity;
    }
}


