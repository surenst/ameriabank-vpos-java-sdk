package am.programming.exceptions;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {
    // General Errors
    APPROVED("00", "Payment successfully completed"),
    ORDER_ALREADY_EXISTS("01", "Order already exists"),
    INCORRECT_CURRENCY("03", "Unknown or forbidden currency"),
    PARAMETER_MISSING("04", "Required parameter is missing"),
    UNREGISTERED_ORDER("06", "Unregistered OrderId"),
    INCORRECT_CREDENTIALS("20", "Incorrect Username and Password"),
    INVALID_OPAQUE("30", "Incorrect Value of opaque field"),
    SYSTEM_ERROR("500", "Unknown error"),
    PARAMETER_ERROR("510", "Incorrect parameters"),
    TIMEOUT_ERROR("520", "Overtime error"),
    OPERATION_FAILED("560", "Operation failed"),
    FRAUD_DETECTED("999", "Declined due to fraud or 3D Secure error"),

    // Additional Error Codes from the provided table
    SERVICE_UNAVAILABLE("0-1", "Processing center response timeout"),
    NO_PAYMENTS_YET("0-100", "No payment attempts"),
    PAN_BLACKLISTED("0-2000", "Transaction declined since the card is blacklisted"),
    IP_BLACKLISTED("0-2001", "Transaction declined since Client’s IP address is blacklisted"),
    BLOCKED_BY_LIMIT("0-20010", "Transaction declined as payment amount exceeded the limits set by the issuer bank"),
    PAYMENT_OVER_LIMIT("0-2002", "Transaction declined as payment amount exceeded the limits"),
    SSL_WITHOUT_CVC_FORBIDDEN("0-2004", "Payment through SSL without entering SVC data is forbidden"),
    SIGNATURE_ERROR("0-2005", "Failed to check the issuer’s signature"),
    THREE_DS_DECLINE("0-2006", "Issuer declined authentication"),
    PAYMENT_TIME_LIMIT_EXCEEDED("0-2007", "Card data registration timeout"),
    PARES_STATUS_UNKNOWN("0-2011", "Issuer bank not ready for 3DS transaction"),
    OPERATION_NOT_SUPPORTED("0-2012", "This operation is not supported"),
    PAYMENT_ATTEMPTS_EXPIRED("0-2013", "Payment attempts expired"),
    CONNECTION_TIMEOUT("0-2018", "Directory server connection timeout"),
    FRAUD_DECLINED("02001", "Fraudulent transaction detected"),
    DUPLICATE_ORDER("08204", "Duplicate order"),
    CARD_DECLINED("0100", "Card declined"),
    EXPIRED_CARD("0101", "Expired card"),
    INVALID_AMOUNT("0110", "Invalid transaction amount"),
    NOT_ENOUGH_MONEY("0116", "Not enough money in the account"),
    INPUT_ERROR("071015", "Incorrect card parameters input"),
    INTERNAL_ERROR("09001", "Internal processing error"),
    INVALID_TRANSACTION("0902", "Cardholder attempts forbidden transaction"),

    // UNKNOWN
    UNKNOWN_ERROR_CODE("", "Unknown error code: ");

    private final String code;
    private final String message;

    private static final Map<String, ErrorCode> CODE_MAP = new HashMap<>();

    // Populate the map with all error codes
    static {
        for (ErrorCode errorCode : values()) {
            CODE_MAP.put(errorCode.code, errorCode);
        }
    }

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Method to get the ErrorCode by error code.
     *
     * @param code The error code to look up.
     * @return The associated message, or a default message if not found.
     */
    public static ErrorCode getErrorCodeByCode(String code) {
        ErrorCode errorCode = CODE_MAP.get(code);
        return errorCode != null ? errorCode :  UNKNOWN_ERROR_CODE;
    }
}
