package am.programming.models.response;

public record ActivateBindingResponse(
        String responseCode,
        String responseMessage
) {}