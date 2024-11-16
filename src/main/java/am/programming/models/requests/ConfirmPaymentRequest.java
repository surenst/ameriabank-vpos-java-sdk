package am.programming.models.requests;

public record ConfirmPaymentRequest(
        String paymentId,
        String username,
        String password,
        double amount
) {}
