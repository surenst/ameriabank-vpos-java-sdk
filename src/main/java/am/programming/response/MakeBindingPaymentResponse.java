package am.programming.response;

public record MakeBindingPaymentResponse(
        String paymentId,
        String responseCode,
        double amount
) {}
