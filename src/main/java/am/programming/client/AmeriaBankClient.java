package am.programming.client;

import am.programming.config.SdkConfig;
import am.programming.utils.JsonUtil;
import am.programming.utils.logging.LogExecution;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static am.programming.utils.ErrorHandlingUtil.handleErrorResponse;
import static am.programming.utils.JsonUtil.fromJson;

@LogExecution
public class AmeriaBankClient {
    private final HttpClientWrapper httpClientWrapper;
    private final SdkConfig config;

    public AmeriaBankClient(SdkConfig config) {
        this.httpClientWrapper = new HttpClientWrapper();
        this.config = config;
    }

    // Need for testing ONLY. Not use in PROD. Don't be dumb
    public AmeriaBankClient(SdkConfig config, HttpClientWrapper httpClientWrapper) {
        this.httpClientWrapper = httpClientWrapper;
        this.config = config;
    }

    public SdkConfig getConfig() {
        return config;
    }

    public <T> T sendRequest(String endpoint, Object request, Class<T> responseType) throws Exception {
        String requestBody = JsonUtil.toJson(request);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(config.getBaseUrl() + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClientWrapper.sendRequest(httpRequest);
        handleErrorResponse(JsonUtil.extractResponseCode(response.body()));
        return fromJson(response.body(), responseType);
    }
}
