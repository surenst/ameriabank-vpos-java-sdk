package am.programming.models.requests;

import am.programming.models.Currency;
import am.programming.models.PaymentType;

import java.math.BigDecimal;

public record MakeBindingPaymentRequest(
        String clientId,           // Required: Merchant ID
        String username,           // Required: Merchant username
        String password,           // Required: Merchant password
        Currency currency,         // Optional: Transaction currency (default to AMD if not provided)
        String description,        // Required: Description of the transaction
        int orderId,               // Required: Unique ID of the transaction
        BigDecimal amount,         // Required: Payment amount
        String opaque,             // Optional: Additional data
        String cardHolderId,       // Required: Unique ID for binding transactions
        String backUrl,            // Required: Callback URL after payment
        PaymentType paymentType    // Required: Payment type (MainRest, PayPal, Binding)
) {
    public MakeBindingPaymentRequest {
        // Set default currency if not provided
        if (currency == null) {
            currency = Currency.AMD;
        }

        // Validate required fields
        if (cardHolderId == null || cardHolderId.isBlank()) {
            throw new IllegalArgumentException("CardHolderID is required");
        }
        if (backUrl == null || backUrl.isBlank()) {
            throw new IllegalArgumentException("BackURL is required");
        }
    }

    public String getCurrencyCode() {
        return currency.getCode();
    }

    public int getPaymentTypeCode() {
        return paymentType.getCode();
    }
}