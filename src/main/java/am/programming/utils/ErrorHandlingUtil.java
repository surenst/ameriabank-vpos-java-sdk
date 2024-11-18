package am.programming.utils;

import am.programming.exceptions.*;
import am.programming.utils.logging.LogExecution;

import java.util.logging.Logger;

@LogExecution
public class ErrorHandlingUtil {

    private static final Logger logger = Logger.getLogger(ErrorHandlingUtil.class.getName());


    private ErrorHandlingUtil() {}

    public static void handleErrorResponse(String responseCode) {
        switch (responseCode) {
            case "00" -> logger.info("Transaction successful.");
            case "01" -> throw new ParameterException(ErrorCode.ORDER_ALREADY_EXISTS);
            case "03" -> throw new ParameterException(ErrorCode.INCORRECT_CURRENCY);
            case "04" -> throw new ParameterException(ErrorCode.PARAMETER_MISSING);
            case "06" -> throw new ParameterException(ErrorCode.UNREGISTERED_ORDER);
            case "20" -> throw new AuthenticationException();
            case "30" -> throw new ParameterException(ErrorCode.INVALID_OPAQUE);
            case "500" -> throw new BaseException(ErrorCode.SYSTEM_ERROR);
            case "510" -> throw new ParameterException(ErrorCode.PARAMETER_ERROR);
            case "520" -> throw new BaseException(ErrorCode.TIMEOUT_ERROR);
            case "560" -> throw new BaseException(ErrorCode.OPERATION_FAILED);
            case "999" -> throw new FraudDetectionException();
            default -> throw new BaseException(ErrorCode.SYSTEM_ERROR);
        }
    }
}
