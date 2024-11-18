package am.programming.models.response;

import java.util.List;

public record GetBindingsResponse(
        String responseCode,
        String responseMessage,
        List<CardBindingFields> bindings
) {}
