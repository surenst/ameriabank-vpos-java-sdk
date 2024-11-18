package am.programming.models.requests;

import am.programming.models.PaymentType;

public record GetBindingsRequest(
        String clientId,
        String username,
        String password,
        PaymentType paymentType
) {}
