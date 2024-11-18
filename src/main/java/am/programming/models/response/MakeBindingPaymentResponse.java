package am.programming.models.response;

import java.math.BigDecimal;

public record MakeBindingPaymentResponse(
        String paymentId,
        String responseCode,
        BigDecimal amount
) {}
