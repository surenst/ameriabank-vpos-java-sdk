package am.programming.exceptions;

public class FraudDetectionException extends BaseException {
    public FraudDetectionException() {
        super(ErrorCode.FRAUD_DETECTED);
    }
}
