package com.example.demo;

import java.util.List;

public class MlResponse {
    private double risk_score;
    private String prediction;
    private List<FeatureImportance> top_features;

    public double getRisk_score() { return risk_score; }
    public void setRisk_score(double v) { risk_score = v; }
    public String getPrediction() { return prediction; }
    public void setPrediction(String v) { prediction = v; }
    public List<FeatureImportance> getTop_features() { return top_features; }
    public void setTop_features(List<FeatureImportance> v) { top_features = v; }

    public static class FeatureImportance {
        private String feature;
        private double importance;

        public String getFeature() { return feature; }
        public void setFeature(String v) { feature = v; }
        public double getImportance() { return importance; }
        public void setImportance(double v) { importance = v; }
    }
}
