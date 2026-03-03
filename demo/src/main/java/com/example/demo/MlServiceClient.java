package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MlServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ml.service.url:http://ml-service:8000}")
    private String mlServiceUrl;

    public MlResponse predict(CreditRequest request) {
        return restTemplate.postForObject(mlServiceUrl + "/predict", request, MlResponse.class);
    }
}
