package am.programming.models.response;

import java.util.List;

public record TransactionListResponse(
        List<TransactionDetails> transactions
) {}

