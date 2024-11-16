package am.programming.response;

public record TransactionDetails(
        double amount,
        String approvalCode,
        String cardNumber,
        String clientName,
        String currency,
        String description,
        String orderId,
        String paymentId,
        String paymentState,
        String paymentType,
        String responseCode,
        String rrn,
        String terminalId,
        String dateTime
) {}
