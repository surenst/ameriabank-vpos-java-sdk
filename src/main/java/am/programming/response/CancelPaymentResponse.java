package am.programming.response;

public record CancelPaymentResponse(
        String responseCode,
        String responseMessage,
        String opaque
) {}
