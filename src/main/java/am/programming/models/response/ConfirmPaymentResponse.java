package am.programming.models.response;

public record ConfirmPaymentResponse(
        String responseCode,
        String responseMessage,
        String opaque
) {}
