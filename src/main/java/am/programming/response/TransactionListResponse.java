package am.programming.response;

import java.util.List;

public record TransactionListResponse(
        List<TransactionDetails> transactions
) {}

