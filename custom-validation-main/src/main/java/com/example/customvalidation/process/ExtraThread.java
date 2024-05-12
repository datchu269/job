package com.example.customvalidation.process;

import com.example.customvalidation.entity.ActionRequest;

import java.util.*;
import java.util.concurrent.*;

public class ExtraThread {

    public static ActionRequest[] response;

    private static final int THREAD_POOL_SIZE = 2;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public synchronized void handle(Map<String, List<ActionRequest>> mapActionRequest) {
        try {
            response = new ActionRequest[12];
            List<ActionRequest> list = mapActionRequest.get("list");

            // Xóa phần tử từ Map sau khi lấy giá trị
            mapActionRequest.remove("list");
            // Thực hiện các luồng cho mỗi entry trong mapActionRequest
            mapActionRequest.forEach((s, actionRequests) ->
                    executorService.submit(new ActonThread(actionRequests)));
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

            // Xử lý list cuối cùng sau khi tất cả các luồng đã hoàn thành
            if (list != null && !list.isEmpty()) {
                ActonThread actonThread = new ActonThread(list);
                actonThread.run();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
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

        List<ActionRequest> requests2 = new ArrayList<>();
        requests2.add(new ActionRequest(3, null, null, null, null));
        requests2.add(new ActionRequest(4, null, null, null, null));
        requests2.add(new ActionRequest(7, null, null, null, null));
        requests2.add(new ActionRequest(9, null, null, null, null));
        mapActionRequest.put("key2", requests2);

        List<ActionRequest> requests3 = new ArrayList<>();
        requests3.add(new ActionRequest(6, null, null, null, null));
        requests3.add(new ActionRequest(8, null, null, null, null));
        requests3.add(new ActionRequest(10, null, null, null, null));
        requests3.add(new ActionRequest(11, null, null, null, null));
        mapActionRequest.put("list", requests3);

        // Sử dụng ExtraThread để xử lý đa luồng và nhận kết quả
        ExtraThread handler = new ExtraThread();
//        List<ActionRequest> result = handler.handleMultiThreadActionRequest(mapActionRequest);
        handler.handle(mapActionRequest);
        // In ra danh sách các ActionRequest đã được xử lý theo thứ tự của ActionIdentifier
        System.out.println("Processed requests: " + response.length);
        List<ActionRequest> re = Arrays.asList(response);
//        re.forEach(e -> System.out.println(e.actionIdentifier));
    }
}
