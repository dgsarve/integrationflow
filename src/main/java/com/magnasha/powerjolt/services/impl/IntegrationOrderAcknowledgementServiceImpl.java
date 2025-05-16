package com.magnasha.powerjolt.services.impl;

import com.magnasha.powerjolt.dto.IntegrationContext;

import com.magnasha.powerjolt.factory.DestinationServiceFactory;
import com.magnasha.powerjolt.model.Order;
import com.magnasha.powerjolt.services.IntegrationOrderAcknowledgementService;
import com.magnasha.powerjolt.services.TransformationService;
import com.magnasha.powerjolt.services.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class IntegrationOrderAcknowledgementServiceImpl implements IntegrationOrderAcknowledgementService {

    private final ValidationService validationService;
    private final TransformationService transformationService;
    private final DestinationServiceFactory destinationServiceFactory;


    public IntegrationOrderAcknowledgementServiceImpl(
            ValidationService validationService,
            TransformationService transformationService,
            DestinationServiceFactory destinationServiceFactory
    ) {
        this.validationService = validationService;
        this.transformationService = transformationService;
        this.destinationServiceFactory = destinationServiceFactory;
    }

    @Override
    public void process(IntegrationContext integrationContext) {
        Order order = new Order();//json to order integrationContext.getPayload();
        integrationContext.setOrder(order);
        Order validatedOrder = validationService.validate(order);
        String transformedPayload = transformationService.transform(order);
        destinationServiceFactory.get(order.getDestinationId()).send(transformedPayload);
    }


}
