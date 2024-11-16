package am.programming.models.requests;

public record TransactionListRequest(
        String clientId,
        String username,
        String password,
        String startDate,
        String endDate
) {}
