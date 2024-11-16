package am.programming.models.requests;

public record CancelPaymentRequest(
        String paymentId,
        String username,
        String password
) {}
