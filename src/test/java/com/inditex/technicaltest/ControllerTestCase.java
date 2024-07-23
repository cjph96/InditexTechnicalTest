package com.inditex.technicaltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class ControllerTestCase extends TechnicalTestApplicationTestCase {

    @Autowired
    private MockMvc mockMvc;

    protected void assertResponse(String endpoint, Integer expectedStatusCode, String expectedResponse) throws Exception {
        ResultMatcher response = expectedResponse.isEmpty() ? content().string("") : content().json(expectedResponse);

        mockMvc.perform(get(endpoint)).andExpect(status().is(expectedStatusCode)).andExpect(response);
    }
}
