package am.programming.response;

public record ConfirmPaymentResponse(
        String responseCode,
        String responseMessage,
        String opaque
) {}
