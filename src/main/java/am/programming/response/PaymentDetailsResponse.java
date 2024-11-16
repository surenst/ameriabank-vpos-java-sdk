package am.programming.response;

public record PaymentDetailsResponse(
        double amount,
        double approvedAmount,
        String approvalCode,
        String cardNumber,
        String clientName,
        String clientEmail,
        String currency,
        String dateTime,
        double depositedAmount,
        String description,
        String merchantId,
        int orderId,
        String paymentState,
        int paymentType,
        String responseCode,
        String rrn,
        String terminalId,
        String bindingId,
        String opaque
) {}
