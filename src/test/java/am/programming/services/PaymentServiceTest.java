package am.programming.services;

import am.programming.client.AmeriaBankClient;
import am.programming.config.SdkConfig;
import am.programming.exceptions.BaseException;
import am.programming.exceptions.ErrorCode;
import am.programming.models.PaymentType;
import am.programming.models.requests.*;
import am.programming.models.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private AmeriaBankClient mockClient;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        // Mock the client and its configuration
        mockClient = mock(AmeriaBankClient.class);
        SdkConfig config = SdkConfig.builder()
                .baseUrl("https://example.com/api")
                .initPaymentPath("/InitPayment")
                .paymentDetailsPath("/PaymentDetails")
                .confirmPaymentPath("/ConfirmPayment")
                .cancelPaymentPath("/CancelPayment")
                .refundPaymentPath("/RefundPayment")
                .username("username")
                .password("password")
                .build();

        when(mockClient.getConfig()).thenReturn(config);
        paymentService = new PaymentService(mockClient);
    }

    @Test
    void testInitPayment_Success() throws Exception {
        // Arrange
        InitPaymentRequest request = new InitPaymentRequest(
                "client123", "username", "password",
                null, "Test Transaction", 123456,
                new BigDecimal("100.00"), "https://callback.url",
                null, null, 1200
        );
        InitPaymentResponse expectedResponse = new InitPaymentResponse("123456", "00", "Payment successful");

        when(mockClient.sendRequest(anyString(), any(), eq(InitPaymentResponse.class)))
                .thenReturn(expectedResponse);

        // Act
        InitPaymentResponse response = paymentService.initPayment(request);

        // Assert
        assertNotNull(response);
        assertEquals("00", response.responseCode());
        assertEquals("Payment successful", response.responseMessage());
    }

    @Test
    void testGetPaymentDetails_Success() throws Exception {
        // Arrange
        PaymentDetailsRequest request = new PaymentDetailsRequest("123456", "username", "password");
        PaymentDetailsResponse expectedResponse = new PaymentDetailsResponse(
                new BigDecimal("100.00"), new BigDecimal("100.00"), "AuthCode123",
                "****1234", "John Doe", "john@example.com", "USD", "2023-10-10T10:00:00",
                new BigDecimal("100.00"), "Test description", "merchant123", "opaqueData", 123456,
                "Completed", PaymentType.MAIN_REST, "00", "RRN123456", "Terminal123",
                "Transaction Description", 1, new BigDecimal("0.00"), "CardHolder123",
                "MDOrder123", "PrimaryRC", "12/24", "192.168.0.1", "Binding123",
                "ActionCode", new BigDecimal("1.0")
        );

        when(mockClient.sendRequest(anyString(), any(), eq(PaymentDetailsResponse.class)))
                .thenReturn(expectedResponse);

        // Act
        PaymentDetailsResponse response = paymentService.getPaymentDetails(request);

        // Assert
        assertNotNull(response);
        assertEquals("00", response.responseCode());
        assertEquals("Completed", response.paymentState());
    }

    @Test
    void testInitPayment_Exception() throws Exception {
        // Arrange
        InitPaymentRequest request = new InitPaymentRequest(
                "client123", "username", "password",
                null, "Test Transaction", 123456,
                new BigDecimal("100.00"), "https://callback.url",
                null, null, 1200
        );

        when(mockClient.sendRequest(anyString(), any(), eq(InitPaymentResponse.class)))
                .thenThrow(new BaseException(ErrorCode.SYSTEM_ERROR));

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> paymentService.initPayment(request));
        assertEquals(ErrorCode.SYSTEM_ERROR.getCode(), exception.getErrorCode());
    }
}
