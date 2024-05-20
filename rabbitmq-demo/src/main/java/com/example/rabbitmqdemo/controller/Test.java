package com.example.rabbitmqdemo.controller;

import com.example.rabbitmqdemo.dao.CustomerDAO;
import com.example.rabbitmqdemo.dao.ObjectTableDAO;
import com.example.rabbitmqdemo.dao.OrderDAO;
import com.example.rabbitmqdemo.entity.Customer;
import com.example.rabbitmqdemo.entity.FieldValidate;
import com.example.rabbitmqdemo.entity.Order;
import com.example.rabbitmqdemo.entity.hi1.ObjectTable;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class Test {
//    @Autowired
//    private ServiceService yourService;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    ObjectTableDAO objectTableDAO;

    @Autowired
    OrderDAO orderDAO;

    @PostMapping("/api/insertData")
    public String insertData() {
        try {

            Order order1 = new Order(1L, new Date());
            Order order2 = new Order(2L, new Date());
            Order order3 = new Order(3L, new Date());

            orderDAO.saveAll(Arrays.asList(order1, order2, order3));
            return null;
            // Gọi phương thức insert từ dịch vụ
//            boolean success = yourService.insert();
////            List<Customer> list = customerDAO.getCustomersWithOrdersAndOrderItems();
////            List<Customer> list = customerDAO.getAllCustomersWithOrdersAndOrderItems();
//            List<ObjectTable> list = objectTableDAO.getAllObjectsWithDetails("Identify_B");
//            String json = "{\"data\":{\"element1\":\"off\", \"element2\":\"on\", \"element3\":\"on\"}}";
//
//            Gson gson = new Gson();
//            FieldValidate myData = gson.fromJson(json, FieldValidate.class);
//
//            // Truy cập dữ liệu
//            Map<String, String> dataMap = myData.getData();
//            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//                System.out.println(entry.getKey() + ": " + entry.getValue());
//            }
//            System.out.println(list);
//            if (success) {
//                return "Insertion successful!";
//            } else {
//                return "Insertion failed!";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during insertion: " + e.getMessage();
        }
    }
}
