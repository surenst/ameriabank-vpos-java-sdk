package am.programming.models;

public enum PaymentType {
    MAIN_REST(5),
    PAYPAL(7),
    BINDING(6);

    private final int code;

    PaymentType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentType fromCode(int code) {
        return switch (code) {
            case 5 -> MAIN_REST;
            case 7 -> PAYPAL;
            case 6 -> BINDING;
            default -> throw new IllegalArgumentException("Unknown payment type code: " + code);
        };
    }
}
