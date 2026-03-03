package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class CreditRiskService {

    public String decide(double riskScore) {
        if (riskScore < 0.3) return "APPROVED";
        if (riskScore > 0.7) return "REJECTED";
        return "MANUAL_REVIEW";
    }
}
