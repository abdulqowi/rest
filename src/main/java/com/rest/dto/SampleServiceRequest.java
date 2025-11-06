package com.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.dto.SampleServiceRq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload")
public class SampleServiceRequest {

    @JsonProperty("sampleservicerq")
    @NotNull(message = "Sample Service Request is required")
    @Valid
    private SampleServiceRq sampleservicerq;
}