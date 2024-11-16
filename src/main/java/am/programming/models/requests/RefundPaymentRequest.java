package am.programming.models.requests;

public record RefundPaymentRequest(
        String paymentId,
        String username,
        String password,
        double amount
) {}
