package com.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload")
public class SampleServiceRequest {

    @JsonProperty("sampleservicerq")
    @NotBlank(message = "Sample Service Request is required")
    @Valid
    private SampleServiceRq sampleservicerq;
}