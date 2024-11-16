package am.programming.response;

import java.util.List;

public record GetBindingsResponse(
        String responseCode,
        String responseMessage,
        List<CardBindingFields> bindings
) {}
