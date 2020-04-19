package com.flight.ticketing.utility;

import com.flight.ticketing.model.enumeration.OperationResultCode;
import com.flight.ticketing.model.response.OperationResult;

public class CommonUtility {

    public static boolean isOperationSuccessfully(OperationResult operationResult) {
        return OperationResultCode.SUCCESS.equals(operationResult.getOperationResultCode());
    }
}
