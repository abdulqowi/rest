package com.rest.controller;

import com.rest.dto.SampleServiceRs;
import com.rest.dto.SampleServiceRequest;
import com.rest.dto.SampleServiceResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sample-service")
@Tag(name = "Sample Service", description = "Sample Service API")
public class SampleServiceController {

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Berhasil")
    })
    public ResponseEntity<SampleServiceResponse> processSampleService(
            @Valid @RequestBody SampleServiceRequest request) {

        SampleServiceRs responseData =
                SampleServiceRs.builder()
                        .errorCode("0000")
                        .errorMsg("Success")
                        .trxId(request.getSampleservicerq().getTrxId())
                        .build();

        SampleServiceResponse response = SampleServiceResponse.builder()
                .sampleservicers(responseData)
                .build();

        return ResponseEntity.ok(response);
    }
}