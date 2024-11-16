package am.programming.response;

public record InitPaymentResponse(
        String paymentId,
        int responseCode,
        String responseMessage
) {}
