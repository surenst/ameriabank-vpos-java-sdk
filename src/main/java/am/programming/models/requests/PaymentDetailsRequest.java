package am.programming.models.requests;

public record PaymentDetailsRequest(
        String paymentId,
        String username,
        String password
) {}
