package am.programming.models.requests;

public record InitPaymentRequest(
        String clientId,
        String username,
        String password,
        double amount,
        int orderId,
        String currency,
        String description,
        String backUrl,
        int timeout,
        String cardHolderId,
        String opaque
) {}
