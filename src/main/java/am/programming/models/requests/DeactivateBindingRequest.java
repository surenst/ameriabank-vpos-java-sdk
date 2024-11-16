package am.programming.models.requests;

public record DeactivateBindingRequest(
        String clientId,
        String username,
        String password,
        String cardHolderId
) {}
