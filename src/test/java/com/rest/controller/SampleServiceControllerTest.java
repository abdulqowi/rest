package com.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.dto.SampleServiceRq;
import com.rest.dto.SampleServiceRequest;
import com.rest.dto.SampleServiceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SampleServiceController.class)
@DisplayName("Sample Service Controller Tests")
class SampleServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper= new ObjectMapper();

    private SampleServiceRequest validRequest;

    @BeforeEach
    void setUp() {
        SampleServiceRq rq = new SampleServiceRq();
        rq.setServiceId("2020042120145512");
        rq.setOrderType("PRO");
        rq.setType("2022112023351");
        rq.setTrxId("c6714ec0-b379-11e9-889b-7f7167f4f72d");

        validRequest = new SampleServiceRequest();
        validRequest.setSampleservicerq(rq);
    }

    @Test
    @DisplayName("Should return success response when request is valid")
    void testProcessSampleService_Success() throws Exception {
        mockMvc.perform(post("/api/sample-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sampleservicers").exists())
                .andExpect(jsonPath("$.sampleservicers.error_code").value("0000"))
                .andExpect(jsonPath("$.sampleservicers.error_msg").value("Success"))
                .andExpect(jsonPath("$.sampleservicers.trx_id").value("c6714ec0-b379-11e9-889b-7f7167f4f72d"));
    }

    @Test
    @DisplayName("Should return 400 when sampleservicerq is null")
    void testProcessSampleService_NullRequest() throws Exception {
        SampleServiceRequest invalidRequest = new SampleServiceRequest();
        invalidRequest.setSampleservicerq(null);

        mockMvc.perform(post("/api/sample-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.sampleservicers.error_code").value("4000"))
                .andExpect(jsonPath("$.sampleservicers.error_msg").exists());
    }

    @Test
    @DisplayName("Should return 400 when service_id is null")
    void testProcessSampleService_NullServiceId() throws Exception {
        validRequest.getSampleservicerq().setServiceId(null);

        mockMvc.perform(post("/api/sample-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return 400 when order_type is null")
    void testProcessSampleService_NullOrderType() throws Exception {
        validRequest.getSampleservicerq().setOrderType(null);

        mockMvc.perform(post("/api/sample-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.sampleservicers.error_code").value("4000"));
    }

    @Test
    @DisplayName("Should return 400 when type is null")
    void test_NullType() throws Exception {
        validRequest.getSampleservicerq().setType(null);

        mockMvc.perform(post("/api/sample-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.sampleservicers.error_code").value("4000"));
    }

    @Test
    @DisplayName("Should return 400 when trx_id is null")
    void test_NullTrxId() throws Exception {
        validRequest.getSampleservicerq().setTrxId(null);

        mockMvc.perform(post("/api/sample-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.sampleservicers.error_code").value("4000"));
    }

    @Test
    @DisplayName("Should return 400 when request body is empty")
    void testProcessSampleService_EmptyBody() throws Exception {
        mockMvc.perform(post("/api/sample-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}