package am.programming.models.response;

public record CancelPaymentResponse(
        String responseCode,
        String responseMessage,
        String opaque
) {}
