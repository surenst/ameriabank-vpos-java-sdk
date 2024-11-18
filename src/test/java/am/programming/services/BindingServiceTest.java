package am.programming.services;

import am.programming.client.AmeriaBankClient;
import am.programming.config.SdkConfig;
import am.programming.exceptions.BaseException;
import am.programming.exceptions.ErrorCode;
import am.programming.models.Currency;
import am.programming.models.PaymentType;
import am.programming.models.requests.*;
import am.programming.models.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BindingServiceTest {

    @Mock
    private AmeriaBankClient mockClient;

    @InjectMocks
    private BindingService bindingService;

    private SdkConfig config;

    @BeforeEach
    void setUp() {
        config = SdkConfig.builder()
                .baseUrl("https://example.com/api")
                .username("myUsername")
                .password("myPassword")
                .makeBindingPaymentPath("/MakeBindingPayment")
                .getBindingsPath("/GetBindings")
                .activateBindingPath("/ActivateBinding")
                .deactivateBindingPath("/DeactivateBinding")
                .initPaymentPath("/InitPayment")
                .confirmPaymentPath("/ConfirmPayment")
                .cancelPaymentPath("/CancelPayment")
                .paymentDetailsPath("/PaymentDetails")
                .refundPaymentPath("/RefundPayment")
                .build();

        when(mockClient.getConfig()).thenReturn(config);
    }

    @Test
    void testMakeBindingPayment_Success() throws Exception {
        MakeBindingPaymentResponse mockResponse = new MakeBindingPaymentResponse(
                "123",
                "00",
                new BigDecimal("10.00"));
        when(mockClient.sendRequest(anyString(), any(MakeBindingPaymentRequest.class), eq(MakeBindingPaymentResponse.class)))
                .thenReturn(mockResponse);

        MakeBindingPaymentRequest request = new MakeBindingPaymentRequest(
                "client123",
                "username",
                "password",
                Currency.AMD,
                "Order payment",
                1,
                new BigDecimal("100.00"),
                "https://example.com/callback",
                "cardHolderId",
                "backUrl",
                PaymentType.MAIN_REST
        );

                MakeBindingPaymentResponse response = bindingService.makeBindingPayment(request);

        assertNotNull(response);
        assertEquals("00", response.responseCode());
    }

    @Test
    void testMakeBindingPayment_Error() throws Exception {
        when(mockClient.sendRequest(anyString(), any(MakeBindingPaymentRequest.class), eq(MakeBindingPaymentResponse.class)))
                .thenThrow(new BaseException(ErrorCode.SYSTEM_ERROR));

        MakeBindingPaymentRequest request = new MakeBindingPaymentRequest(
                "client123",
                "username",
                "password",
                Currency.AMD,
                "Order payment",
                1,
                new BigDecimal("100.00"),
                "https://example.com/callback",
                "cardHolderId",
                "backUrl",
                PaymentType.MAIN_REST
        );

        assertThrows(BaseException.class, () -> bindingService.makeBindingPayment(request));
    }

    @Test
    void testGetBindings_Success() throws Exception {
        GetBindingsResponse mockResponse = new GetBindingsResponse(
                "00",
                "Bindings retrieved successfully",
                List.of(new CardBindingFields(
                        "cardPan",
                        "expDate",
                        true,
                        "cardHolderId"
                ))
        );
        when(mockClient.sendRequest(anyString(), any(GetBindingsRequest.class), eq(GetBindingsResponse.class)))
                .thenReturn(mockResponse);

        GetBindingsRequest request = new GetBindingsRequest(
                "client123",
                "username",
                "password",
                PaymentType.MAIN_REST
        );
        GetBindingsResponse response = bindingService.getBindings(request);

        assertNotNull(response);
        assertEquals("00", response.responseCode());
        assertEquals("Bindings retrieved successfully", response.responseMessage());
    }

    @Test
    void testActivateBinding_Success() throws Exception {
        ActivateBindingResponse mockResponse = new ActivateBindingResponse("00", "Binding activated successfully");
        when(mockClient.sendRequest(anyString(), any(ActivateBindingRequest.class), eq(ActivateBindingResponse.class)))
                .thenReturn(mockResponse);

        ActivateBindingRequest request = new ActivateBindingRequest("client123", "username", "password", "bindingId123");
        ActivateBindingResponse response = bindingService.activateBinding(request);

        assertNotNull(response);
        assertEquals("00", response.responseCode());
        assertEquals("Binding activated successfully", response.responseMessage());
    }

    @Test
    void testDeactivateBinding_Success() throws Exception {
        DeactivateBindingResponse mockResponse = new DeactivateBindingResponse("00", "Binding deactivated successfully");
        when(mockClient.sendRequest(anyString(), any(DeactivateBindingRequest.class), eq(DeactivateBindingResponse.class)))
                .thenReturn(mockResponse);

        DeactivateBindingRequest request = new DeactivateBindingRequest("client123", "username", "password", "bindingId123");
        DeactivateBindingResponse response = bindingService.deactivateBinding(request);

        assertNotNull(response);
        assertEquals("00", response.responseCode());
        assertEquals("Binding deactivated successfully", response.responseMessage());
    }

    @Test
    void testActivateBinding_Error() throws Exception {
        when(mockClient.sendRequest(anyString(), any(ActivateBindingRequest.class), eq(ActivateBindingResponse.class)))
                .thenThrow(new BaseException(ErrorCode.SYSTEM_ERROR));

        ActivateBindingRequest request = new ActivateBindingRequest("client123", "username", "password", "bindingId123");

        assertThrows(BaseException.class, () -> bindingService.activateBinding(request));
    }

    @Test
    void testDeactivateBinding_Error() throws Exception {
        when(mockClient.sendRequest(anyString(), any(DeactivateBindingRequest.class), eq(DeactivateBindingResponse.class)))
                .thenThrow(new BaseException(ErrorCode.SYSTEM_ERROR));

        DeactivateBindingRequest request = new DeactivateBindingRequest("client123", "username", "password", "bindingId123");

        assertThrows(BaseException.class, () -> bindingService.deactivateBinding(request));
    }
}