package com.flight.ticketing.model.response;

import com.flight.ticketing.model.enumeration.OperationResultCode;
import lombok.Data;

@Data
public class OperationResult {
    private static OperationResult instance;
    private OperationResultCode operationResultCode;
    private String description;

    private OperationResult(OperationResultCode operationResultCode) {
        this.operationResultCode = operationResultCode;
    }

    public static OperationResult newIstance(OperationResultCode operationResultCode) {
        return new OperationResult(operationResultCode);
    }

    public static OperationResult createSuccessResult() {
        return newIstance(OperationResultCode.SUCCESS);
    }

    public static OperationResult createSuccessResult(String description) {
        OperationResult operationResult = newIstance(OperationResultCode.SUCCESS);
        operationResult.setDescription(description);
        return operationResult;
    }

    public static OperationResult createErrorResult() {
        return newIstance(OperationResultCode.ERROR);
    }

    public static OperationResult createErrorResult(String description) {
        OperationResult operationResult = newIstance(OperationResultCode.ERROR);
        operationResult.setDescription(description);
        return operationResult;
    }
}
