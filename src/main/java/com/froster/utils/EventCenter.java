package com.froster.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventCenter {
    private static final Map<String, List<Consumer<Event>>> eventMap = new HashMap<>();
    public static void publish(String type, Event event) {
        if (!eventMap.containsKey(type)) return;
        eventMap.get(type).forEach(e -> e.accept(event));
    }

    public static void subscribe(String type, Consumer<Event> callback) {
        if (!eventMap.containsKey(type)) {
            eventMap.put(type, new ArrayList<>());
        }
        eventMap.get(type).add(callback);
    }
}
