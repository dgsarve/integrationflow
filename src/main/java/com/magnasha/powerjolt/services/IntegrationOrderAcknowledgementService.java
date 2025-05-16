package com.magnasha.powerjolt.services;

import com.magnasha.powerjolt.dto.IntegrationContext;

public interface IntegrationOrderAcknowledgementService {
    void process(IntegrationContext rawPayload);
}
