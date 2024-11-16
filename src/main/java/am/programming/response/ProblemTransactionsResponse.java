package am.programming.response;

import java.util.List;

public record ProblemTransactionsResponse(
        List<ProblemTransactionDetails> problems
) {}
