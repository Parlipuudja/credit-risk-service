package com.example.demo;

import java.util.List;

public class CreditRiskResponse {
    private String decision;
    private double riskScore;
    private List<MlResponse.FeatureImportance> topFeatures;

    public CreditRiskResponse(String decision, double riskScore, List<MlResponse.FeatureImportance> topFeatures) {
        this.decision = decision;
        this.riskScore = riskScore;
        this.topFeatures = topFeatures;
    }

    public String getDecision() { return decision; }
    public double getRiskScore() { return riskScore; }
    public List<MlResponse.FeatureImportance> getTopFeatures() { return topFeatures; }
}
