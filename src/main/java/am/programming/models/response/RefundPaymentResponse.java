package am.programming.models.response;

public record RefundPaymentResponse(
        String responseCode,
        String responseMessage,
        String opaque
) {}