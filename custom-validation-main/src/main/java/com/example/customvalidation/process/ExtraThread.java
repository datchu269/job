package com.example.customvalidation.process;

import com.example.customvalidation.entity.ActionRequest;
import com.example.customvalidation.entity.Payload;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExtraThread {

    private static final int THREAD_POOL_SIZE = 5;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public List<ActionRequest> handleMultiThreadActionRequest(Map<String, List<ActionRequest>> mapActionRequest) {
        Long time = System.currentTimeMillis();
        List<Future<List<ActionRequest>>> futures = new ArrayList<>();
        // Duyệt qua từng entry trong mapActionRequest
        mapActionRequest.forEach((key, actionRequests) -> {
            // Submit một Callable để xử lý danh sách actionRequests và trả về danh sách đã xử lý
            Future<List<ActionRequest>> future = executorService.submit(() -> processHandleActionRequest(actionRequests));
            futures.add(future);
        });

        List<ActionRequest> processedRequests = new ArrayList<>();

        // Lấy kết quả từ các Future và thu thập tất cả các ActionRequest đã được xử lý
        for (Future<List<ActionRequest>> future : futures) {
            try {
                List<ActionRequest> processedList = future.get(); // Lấy kết quả từ Future
                processedRequests.addAll(processedList);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace(); // Xử lý lỗi nếu cần
            }
        }

//         Sắp xếp lại danh sách kết quả theo ActionIdentifier tăng dần
//        processedRequests.sort(Comparator.comparingInt(ActionRequest::getActionIdentifier));

//         Tắt ExecutorService sau khi sử dụng
        executorService.shutdown();

        // Trả về danh sách các ActionRequest đã được xử lý và sắp xếp theo thứ tự của ActionIdentifier
        Long time2 = System.currentTimeMillis();
        System.out.println("time " + (time2 - time));
        return processedRequests;
    }

    private List<ActionRequest> processHandleActionRequest(List<ActionRequest> actionRequests) {
        // Xử lý từng ActionRequest trong danh sách và trả về danh sách đã được xử lý
        List<ActionRequest> processedList = new ArrayList<>();
        for (ActionRequest actionRequest : actionRequests) {
            for (int i = 0; i < 1000000000; i++) {

            }
            for (int i = 0; i < 1000000000; i++) {

            }
            for (int i = 0; i < 1000000000; i++) {

            }
            // Xử lý actionRequest (ví dụ: có thể gọi processHandleActionResponse(actionRequest))
            ActionRequest processedRequest = processHandleActionResponse(actionRequest);
            processedList.add(processedRequest);
        }
        return processedList;
    }

    private ActionRequest processHandleActionResponse(ActionRequest actionRequest) {
        // Xử lý một ActionRequest cụ thể và trả về kết quả
        // Ví dụ đơn giản, ở đây chỉ trả về ActionRequest ban đầu
        return actionRequest;
    }

    // Class đại diện cho Payload
    static class Payload {
        private List<ActionRequest> processedRequests;

        public Payload(List<ActionRequest> processedRequests) {
            this.processedRequests = processedRequests;
        }

        public List<ActionRequest> getProcessedRequests() {
            return processedRequests;
        }
    }


    public static void main(String[] args) {
        // Khởi tạo đầu vào mapActionRequest với một danh sách ActionRequest giả định
        Map<String, List<ActionRequest>> mapActionRequest = new HashMap<>();
        List<ActionRequest> requests = new ArrayList<>();
        requests.add(new ActionRequest(1, null, null, null, null));
        requests.add(new ActionRequest(2, null, null, null, null));
        requests.add(new ActionRequest(5, null, null, null, null));
        requests.add(new ActionRequest(12, null, null, null, null));
        mapActionRequest.put("key1", requests);

        List<ActionRequest> requests2= new ArrayList<>();
        requests.add(new ActionRequest(3, null, null, null, null));
        requests.add(new ActionRequest(4, null, null, null, null));
        requests.add(new ActionRequest(7, null, null, null, null));
        requests.add(new ActionRequest(9, null, null, null, null));
        mapActionRequest.put("key2", requests2);

        List<ActionRequest> requests3= new ArrayList<>();
        requests.add(new ActionRequest(6, null, null, null, null));
        requests.add(new ActionRequest(8, null, null, null, null));
        requests.add(new ActionRequest(10, null, null, null, null));
        requests.add(new ActionRequest(11, null, null, null, null));
        mapActionRequest.put("key3", requests2);

        // Sử dụng ExtraThread để xử lý đa luồng và nhận kết quả
        ExtraThread handler = new ExtraThread();
        List<ActionRequest> result = handler.handleMultiThreadActionRequest(mapActionRequest);

        // In ra danh sách các ActionRequest đã được xử lý theo thứ tự của ActionIdentifier
        System.out.println("Processed requests:");
        for (ActionRequest request : result) {
            System.out.println("ActionIdentifier: " + request.getActionIdentifier());
        }
    }
}
