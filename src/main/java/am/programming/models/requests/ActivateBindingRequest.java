package am.programming.models.requests;

public record ActivateBindingRequest(
        String clientId,
        String username,
        String password,
        String cardHolderId
) {}
