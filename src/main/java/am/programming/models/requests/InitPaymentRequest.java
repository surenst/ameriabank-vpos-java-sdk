package am.programming.models.requests;

import am.programming.models.Currency;

import java.math.BigDecimal;

public record InitPaymentRequest(
        String clientId,           // Required: Merchant ID
        String username,           // Required: Merchant username
        String password,           // Required: Merchant password
        Currency currency,         // Optional: Transaction currency (ISO code)
        String description,        // Required: Description of the transaction
        int orderId,               // Required: Unique ID of the transaction
        BigDecimal amount,         // Required: Payment amount
        String backUrl,            // Optional: Callback URL after payment
        String opaque,             // Optional: Additional data
        String cardHolderId,       // Optional: Unique ID for binding transactions
        Integer timeout            // Optional: Session duration in seconds (default 1200)
) {
    public InitPaymentRequest {
        // Set default currency if not provided
        if (currency == null) {
            currency = Currency.AMD;
        }

        // Validate Timeout
        if (timeout == null || timeout > 1200) {
            timeout = 1200; // Default to 1200 seconds (20 minutes)
        }
    }

    public String getCurrencyCode() {
        return currency.getCode();
    }
}