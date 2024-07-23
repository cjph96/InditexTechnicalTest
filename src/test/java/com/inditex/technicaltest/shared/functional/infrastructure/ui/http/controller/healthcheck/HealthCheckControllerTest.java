package com.inditex.technicaltest.shared.functional.infrastructure.ui.http.controller.healthcheck;

import com.inditex.technicaltest.ControllerTestCase;
import org.junit.jupiter.api.Test;

public class HealthCheckControllerTest extends ControllerTestCase {

    @Test
    void shouldReturnStatusOK() throws Exception {
        assertResponse("/health-check", 200, "{'status': 'OK'}");
    }
}
