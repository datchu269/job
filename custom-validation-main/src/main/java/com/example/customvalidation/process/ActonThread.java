package com.example.customvalidation.process;

import com.example.customvalidation.entity.ActionRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ActonThread extends Thread {
    private final List<ActionRequest> actionRequestList;

    @Override
    public void run() {
        for (ActionRequest actionRequest : actionRequestList) {
            for (int i = 0; i < 1000000; i++) {

            }
            int index = actionRequest.actionIdentifier;
            System.err.println("index ----->" + index);
//            ExtraThread.response[actionRequest.actionIdentifier] = actionRequest;
            ExtraThread.response[index] = actionRequest;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
