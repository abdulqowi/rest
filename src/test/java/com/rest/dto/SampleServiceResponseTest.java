package com.rest.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sample Service Response DTO Tests")
class SampleServiceResponseTest {

    @Test
    void testBuilderPattern() {
        SampleServiceRs rs = SampleServiceRs.builder()
                .errorCode("0000")
                .errorMsg("Success")
                .trxId("test-trx-123")
                .build();

        SampleServiceResponse response = SampleServiceResponse.builder()
                .sampleservicers(rs)
                .build();

        assertNotNull(response);
        assertNotNull(response.getSampleservicers());
        assertEquals("0000", response.getSampleservicers().getErrorCode());
        assertEquals("Success", response.getSampleservicers().getErrorMsg());
        assertEquals("test-trx-123", response.getSampleservicers().getTrxId());
    }

    @Test
    void testConstructor() {
        SampleServiceRs rs = new SampleServiceRs("0000", "Success", "trx-456");
        SampleServiceResponse response = new SampleServiceResponse(rs);

        assertNotNull(response);
        assertEquals("0000", response.getSampleservicers().getErrorCode());
        assertEquals("Success", response.getSampleservicers().getErrorMsg());
        assertEquals("trx-456", response.getSampleservicers().getTrxId());
    }

    @Test
    void testEqualsAndHashCode() {
        SampleServiceRs rs1 = new SampleServiceRs("0000", "Success", "trx-123");
        SampleServiceResponse response1 = new SampleServiceResponse(rs1);

        SampleServiceRs rs2 = new SampleServiceRs("0000", "Success", "trx-123");
        SampleServiceResponse response2 = new SampleServiceResponse(rs2);

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
    }

    @Test
    void testToString() {
        SampleServiceRs rs = new SampleServiceRs("0000", "Success", "trx-789");
        SampleServiceResponse response = new SampleServiceResponse(rs);

        String toString = response.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("SampleServiceResponse"));
    }
}