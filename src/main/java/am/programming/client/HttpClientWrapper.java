package am.programming.client;

import am.programming.utils.logging.LogExecution;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@LogExecution
public class HttpClientWrapper {
    private final HttpClient client;

    public HttpClientWrapper() {
        this.client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> sendRequest(HttpRequest request) throws Exception {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
