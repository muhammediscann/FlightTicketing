package com.flight.ticketing.exception;

import com.flight.ticketing.model.response.OperationResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OperationResultException extends RuntimeException {

    private final OperationResult operationResult;

    public OperationResultException(OperationResult operationResult) {
        super(operationResult.getOperationResultCode().name());
        this.operationResult = operationResult;
    }
}
