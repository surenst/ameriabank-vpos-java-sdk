package am.programming.client;

import am.programming.config.SdkConfig;
import am.programming.exceptions.BaseException;
import am.programming.models.Currency;
import am.programming.models.requests.InitPaymentRequest;
import am.programming.models.response.InitPaymentResponse;
import am.programming.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AmeriaBankClientTest {
    private HttpClientWrapper mockHttpClientWrapper;
    private HttpResponse<String> mockHttpResponse;
    private AmeriaBankClient client;

    @BeforeEach
    void setUp() {
        mockHttpClientWrapper = mock(HttpClientWrapper.class);
        mockHttpResponse = mock(HttpResponse.class);

        SdkConfig config = SdkConfig.builder()
                .baseUrl("https://example.com/api")
                .username("username")
                .password("password")
                .build();
        client = new AmeriaBankClient(config, mockHttpClientWrapper);
    }

    @Test
    void testSendRequest_SuccessfulResponse() throws Exception {
        // Arrange
        InitPaymentResponse mockResponse = new InitPaymentResponse("123456", "00", "Payment successful");
        String responseBody = JsonUtil.toJson(mockResponse);

        when(mockHttpResponse.body()).thenReturn(responseBody);
        when(mockHttpClientWrapper.sendRequest(any(HttpRequest.class))).thenReturn(mockHttpResponse);

        // Create a valid InitPaymentRequest object with all 11 arguments
        InitPaymentRequest request = new InitPaymentRequest(
                "client123",               // clientId
                "username",                // username
                "password",                // password
                Currency.USD,              // currency
                "Order Payment",           // description
                789012,                    // orderId
                new BigDecimal("100.00"), // amount
                "https://example.com/callback", // backUrl
                null,                      // opaque
                null,                      // cardHolderId
                900                        // timeout (seconds)
        );

        // Act
        InitPaymentResponse response = client.sendRequest("/InitPayment", request, InitPaymentResponse.class);

        // Assert
        assertNotNull(response);
        assertEquals("123456", response.paymentId());
        assertEquals("00", response.responseCode());
        assertEquals("Payment successful", response.responseMessage());
    }



    @Test
    void testSendRequest_ErrorResponse() throws Exception {
        // Arrange
        String errorResponse = "{\"responseCode\":\"06\"}";
        when(mockHttpResponse.body()).thenReturn(errorResponse);
        when(mockHttpClientWrapper.sendRequest(any(HttpRequest.class))).thenReturn(mockHttpResponse);

        // Act & Assert
        assertThrows(BaseException.class, () -> {
            client.sendRequest("/InitPayment", new Object(), InitPaymentResponse.class);
        });
    }

    @Test
    void testSendRequest_HttpClientException() throws Exception {
        // Arrange
        when(mockHttpClientWrapper.sendRequest(any(HttpRequest.class)))
                .thenThrow(new RuntimeException("Network Error"));

        InitPaymentRequest request = new InitPaymentRequest(
                "client123", "username", "password", Currency.USD,
                "Order Payment", 789012, new BigDecimal("100.00"),
                "https://example.com/callback", null, null, 900
        );

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () ->
                client.sendRequest("/InitPayment", request, InitPaymentResponse.class)
        );

        assertEquals("Network Error", exception.getMessage());
    }

}
