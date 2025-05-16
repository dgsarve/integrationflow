package com.magnasha.powerjolt.factory;

import com.magnasha.powerjolt.services.destinations.DestinationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DestinationServiceFactory {

    private final Map<String, DestinationService> destinationServiceMap;

    public DestinationServiceFactory(Map<String, DestinationService> destinationServiceMap) {
        this.destinationServiceMap = destinationServiceMap;
    }

    public DestinationService get(String destinationId) {
        return destinationServiceMap.get(destinationId);
    }
}