package com.magnasha.powerjolt.services;

import com.magnasha.powerjolt.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransformationService {

    private final Map<String, String> joltSpecMap;

    public TransformationService(@Value("#{${spring.application.joltspecById}}") Map<String, String> joltSpecMap) {
        this.joltSpecMap = joltSpecMap;
    }

    public String transform(Order order) {
        String key = order.getSourceId() + "-" + order.getDestinationId();
        String specJson = joltSpecMap.get(key);
        return "";
    }
}
