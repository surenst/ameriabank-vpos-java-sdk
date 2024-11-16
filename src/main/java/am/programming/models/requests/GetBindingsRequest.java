package am.programming.models.requests;

public record GetBindingsRequest(
        String clientId,
        String username,
        String password,
        int paymentType
) {}
