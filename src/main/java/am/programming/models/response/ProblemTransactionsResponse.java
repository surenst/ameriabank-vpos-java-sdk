package am.programming.models.response;

import java.util.List;

public record ProblemTransactionsResponse(
        List<ProblemTransactionDetails> problems
) {}
