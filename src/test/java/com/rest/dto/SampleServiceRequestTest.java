package com.rest.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SampleServiceRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidRequest() {
        SampleServiceRq rq = new SampleServiceRq();
        rq.setServiceId("2020042120145512");
        rq.setOrderType("PRO");
        rq.setType("2022112023351");
        rq.setTrxId("c6714ec0-b379-11e9-889b-7f7167f4f72d");

        SampleServiceRequest request = new SampleServiceRequest();
        request.setSampleservicerq(rq);

        Set<ConstraintViolation<SampleServiceRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Should have no validation errors");
    }

    @Test
    void testNullSampleServiceRq() {
        SampleServiceRequest request = new SampleServiceRequest();
        request.setSampleservicerq(null);

        Set<ConstraintViolation<SampleServiceRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Should have validation errors");
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("required"));
    }

    @Test
    void testNullServiceId() {
        SampleServiceRq rq = new SampleServiceRq();
        rq.setServiceId(null);
        rq.setOrderType("PRO");
        rq.setType("2022112023351");
        rq.setTrxId("c6714ec0-b379-11e9-889b-7f7167f4f72d");

        SampleServiceRequest request = new SampleServiceRequest();
        request.setSampleservicerq(rq);

        Set<ConstraintViolation<SampleServiceRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Should have validation errors");
    }

    @Test
    void testLombokMethods() {
        SampleServiceRq rq = new SampleServiceRq();
        rq.setServiceId("123");
        rq.setOrderType("PRO");
        rq.setType("456");
        rq.setTrxId("789");

        SampleServiceRequest request1 = new SampleServiceRequest();
        request1.setSampleservicerq(rq);

        SampleServiceRequest request2 = new SampleServiceRequest();
        request2.setSampleservicerq(rq);

        assertEquals(request1, request2);

        assertEquals(request1.hashCode(), request2.hashCode());

        assertNotNull(request1.toString());
        assertTrue(request1.toString().contains("SampleServiceRequest"));

        assertNotNull(request1.getSampleservicerq());
        assertEquals("123", request1.getSampleservicerq().getServiceId());
    }
}