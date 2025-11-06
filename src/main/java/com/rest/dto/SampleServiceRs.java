package com.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleServiceRs {
    @JsonProperty("error_code")
    @Schema(description = "Error code", example = "0000")
    private String errorCode;

    @JsonProperty("error_msg")
    @Schema(description = "Error message", example = "Success")
    private String errorMsg;

    @JsonProperty("trx_id")
    @Schema(description = "Transaction ID", example = "trx id from request")
    private String trxId;
}
