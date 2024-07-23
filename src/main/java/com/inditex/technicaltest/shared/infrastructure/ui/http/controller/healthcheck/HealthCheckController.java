package com.inditex.technicaltest.shared.infrastructure.ui.http.controller.healthcheck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    public HealthCheckController() {
    }

    @GetMapping(value = "/health-check", produces = "application/json")
    public ResponseEntity<HealthCheckResponse> healthCheck() {
        return ResponseEntity.ok(new HealthCheckResponse(ServiceStatus.OK));
    }
}
