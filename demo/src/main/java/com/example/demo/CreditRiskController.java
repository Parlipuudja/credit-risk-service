package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CreditRiskController {

    @Autowired
    private MlServiceClient mlClient;

    @Autowired
    private CreditRiskService riskService;

    @PostMapping("/credit-risk")
    public ResponseEntity<CreditRiskResponse> assessRisk(@RequestBody CreditRequest request) {
        MlResponse ml = mlClient.predict(request);
        String decision = riskService.decide(ml.getRisk_score());
        CreditRiskResponse response = new CreditRiskResponse(decision, ml.getRisk_score(), ml.getTop_features());
        return ResponseEntity.ok(response);
    }

    public static void main(String[] args) {
        SpringApplication.run(CreditRiskController.class, args);
    }
}
