package am.programming.utils;

import am.programming.exceptions.BaseException;
import am.programming.exceptions.ErrorCode;
import am.programming.utils.logging.LogExecution;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@LogExecution
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtil(){}

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new BaseException(ErrorCode.SYSTEM_ERROR);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new BaseException(ErrorCode.SYSTEM_ERROR);
        }
    }

    public static String extractResponseCode(String jsonResponse) throws Exception {
        JsonNode rootNode = mapper.readTree(jsonResponse);

        // Ensure the JSON response has a "responseCode" field
        if (rootNode == null || !rootNode.has("responseCode")) {
            throw new BaseException(ErrorCode.PARAMETER_MISSING);
        }

        return rootNode.get("responseCode").asText();
    }
}
