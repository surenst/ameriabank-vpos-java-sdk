package am.programming.models.response;

import am.programming.models.PaymentType;

import java.math.BigDecimal;

public record PaymentDetailsResponse(
        BigDecimal amount,              // Transaction amount
        BigDecimal approvedAmount,      // Amount blocked on the client’s card
        String approvalCode,            // Transaction authorization code
        String cardNumber,              // Masked card number
        String clientName,              // Cardholder name
        String clientEmail,             // Cardholder’s email address
        String currency,                // Transaction currency
        String dateTime,                // Transaction date
        BigDecimal depositedAmount,     // Amount deposited to the merchant’s account
        String description,             // Information about the transaction
        String merchantId,              // Merchant ID
        String opaque,                 // Additional data
        int orderId,                    // Unique ID of the transaction
        String paymentState,            // Payment state
        PaymentType paymentType,        // Payment type (enum)
        String responseCode,            // Operation response code (successful=00)
        String rrn,                     // Unique code of the transaction
        String terminalId,              // Merchant’s terminal ID
        String trxnDescription,         // Description of the transaction
        int orderStatus,                // Status code of the payment
        BigDecimal refundedAmount,      // Amount refunded back to the card
        String cardHolderId,            // Unique ID for binding transactions
        String mdOrderId,               // Payment system identifier
        String primaryRc,               // Main response code
        String expDate,                 // Card expiration date
        String processingIp,            // IP address
        String bindingId,               // Binding identifier
        String actionCode,              // Action code
        BigDecimal exchangeRate         // Exchange rate
) {}
