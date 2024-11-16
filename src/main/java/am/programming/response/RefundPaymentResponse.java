package am.programming.response;

public record RefundPaymentResponse(
        String responseCode,
        String responseMessage,
        String opaque
) {}