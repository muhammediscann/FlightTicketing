package com.flight.ticketing.controller.advice;

import com.flight.ticketing.exception.OperationResultException;
import com.flight.ticketing.model.response.OperationResultExceptionResponse;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class GeneralControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ExceptionHandler(OperationResultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OperationResultExceptionResponse handleDomainException(OperationResultException ex) {
        return OperationResultExceptionResponse.builder().operationResult(ex.getOperationResult()).build();
    }
}
