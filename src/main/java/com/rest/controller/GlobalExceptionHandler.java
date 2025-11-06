package com.rest.controller;

import com.rest.dto.SampleServiceRs;
import com.rest.dto.SampleServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SampleServiceResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        SampleServiceRs responseData =
                SampleServiceRs.builder()
                        .errorCode("4000")
                        .errorMsg("Validasi gagal: " + errorMessage)
                        .trxId(null)
                        .build();

        SampleServiceResponse response = SampleServiceResponse.builder()
                .sampleservicers(responseData)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<SampleServiceResponse> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {

        SampleServiceRs responseData =
                SampleServiceRs.builder()
                        .errorCode("4001")
                        .errorMsg("Request body tidak valid atau format JSON salah")
                        .trxId(null)
                        .build();

        SampleServiceResponse response = SampleServiceResponse.builder()
                .sampleservicers(responseData)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SampleServiceResponse> handleGeneralException(Exception ex) {

        SampleServiceRs responseData =
                SampleServiceRs.builder()
                        .errorCode("5000")
                        .errorMsg("Terjadi kesalahan: " + ex.getMessage())
                        .trxId(null)
                        .build();

        SampleServiceResponse response = SampleServiceResponse.builder()
                .sampleservicers(responseData)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<SampleServiceResponse> handleNullPointerException(
            NullPointerException ex) {

        SampleServiceRs responseData =
                SampleServiceRs.builder()
                        .errorCode("5001")
                        .errorMsg("Data tidak ditemukan atau null")
                        .trxId(null)
                        .build();

        SampleServiceResponse response = SampleServiceResponse.builder()
                .sampleservicers(responseData)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}