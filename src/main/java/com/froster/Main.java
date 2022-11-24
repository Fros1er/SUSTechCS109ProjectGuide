package com.froster;

import com.froster.utils.EventCenter;
import com.froster.utils.ExampleEvent;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        EventCenter.subscribe("aaa", (e) -> {
            ExampleEvent casted = (ExampleEvent) e;
            System.out.println(casted.a);
        });

        // somewhere else:
        EventCenter.publish("aaa", new ExampleEvent(10));
    }
}