package am.programming.models.requests;

public record MakeBindingPaymentRequest(
        String clientId,
        String username,
        String password,
        String cardHolderId,
        double amount,
        int orderId,
        String backUrl,
        int paymentType,
        String description,
        String currency
) {}
