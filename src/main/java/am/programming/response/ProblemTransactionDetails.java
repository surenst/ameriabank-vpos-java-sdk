package am.programming.response;

public record ProblemTransactionDetails(
        String cardNumber,
        String errorMessage,
        String orderId,
        String paymentDate,
        double paymentSum
) {}
