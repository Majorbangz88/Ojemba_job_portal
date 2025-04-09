package com.big_joe.Ojemba_Job_Portal.company;

import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
import com.big_joe.Ojemba_Job_Portal.company.exception.CompanyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<CompanyResponse> handleCompanyNotFound(CompanyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CompanyUtils.buildCompanyResponse(
                        CompanyUtils.COMPANY_NOT_EXIST_CODE,
                        ex.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CompanyResponse> handleGenericException(Exception ex) {
        log.error("Unhandled Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CompanyUtils.buildCompanyResponse(
                        CompanyUtils.INTERNAL_SERVER_ERROR_CODE,
                        "An unexpected error occurred. Please try again later.",
                        null
                ));
    }
}

