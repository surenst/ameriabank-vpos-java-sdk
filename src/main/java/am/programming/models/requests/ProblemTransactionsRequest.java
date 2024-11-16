package am.programming.models.requests;

public record ProblemTransactionsRequest(
        String clientId,
        String username,
        String password,
        String startDate,
        String endDate
) {}
