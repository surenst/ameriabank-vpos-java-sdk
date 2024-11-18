package am.programming.models.response;

public record CardBindingFields(
        String cardPan,
        String expDate,
        boolean isActive,
        String cardHolderId
) {}
