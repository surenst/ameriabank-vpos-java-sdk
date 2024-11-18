package am.programming.models.response;

public record InitPaymentResponse(
        String paymentId,
        String responseCode,
        String responseMessage
) {}
