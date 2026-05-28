package org.vaadin.numerosity.rest;

import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Lightweight health endpoint for external integrators.
 */
@RestController
@RequestMapping({"/api/health", "/api/v1/health"})
public class ApiHealthController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "service", "numerosity",
                "timestamp", OffsetDateTime.now().toString()
        ));
    }
}
