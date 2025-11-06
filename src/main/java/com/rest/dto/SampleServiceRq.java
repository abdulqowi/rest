package com.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleServiceRq {

    @JsonProperty("service_id")
    @Schema(description = "Service ID", example = "2020042120145512")
    @NotBlank(message = "Service ID cant be null")
    private String serviceId;

    @JsonProperty("order_type")
    @Schema(description = "Tipe order", example = "PRO")
    @NotBlank(message = "Order Type cant be null")
    private String orderType;

    @JsonProperty("type")
    @Schema(description = "Type", example = "2022112023351")
    @NotBlank(message = "Type is required")
    private String type;

    @JsonProperty("trx_id")
    @Schema(description = "Transaction ID", example = "c6714ec0-b379-11e9-889b-7f7167f4f72d")
    @NotBlank(message = "Transaction ID is required")
    private String trxId;
}

