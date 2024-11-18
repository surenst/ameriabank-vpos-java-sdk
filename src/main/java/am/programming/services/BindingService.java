package am.programming.services;

import am.programming.client.AmeriaBankClient;
import am.programming.exceptions.BaseException;
import am.programming.exceptions.ErrorCode;
import am.programming.models.requests.ActivateBindingRequest;
import am.programming.models.requests.DeactivateBindingRequest;
import am.programming.models.requests.GetBindingsRequest;
import am.programming.models.requests.MakeBindingPaymentRequest;
import am.programming.models.response.ActivateBindingResponse;
import am.programming.models.response.DeactivateBindingResponse;
import am.programming.models.response.GetBindingsResponse;
import am.programming.models.response.MakeBindingPaymentResponse;
import am.programming.utils.logging.LogExecution;

@LogExecution
public class BindingService {
    private final AmeriaBankClient client;

    public BindingService(AmeriaBankClient client) {
        this.client = client;
    }

    /**
     * Make a payment using a card binding.
     */
    public MakeBindingPaymentResponse makeBindingPayment(MakeBindingPaymentRequest request) {
        String path = client.getConfig().getMakeBindingPaymentPath();
        try {
            MakeBindingPaymentResponse response = client.sendRequest(path, request, MakeBindingPaymentResponse.class);
            handleResponseCode(response.responseCode());
            return response;
        } catch (Exception e) {
            throw new BaseException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * Get a list of card bindings.
     */
    public GetBindingsResponse getBindings(GetBindingsRequest request) {
        String path = client.getConfig().getGetBindingsPath();
        try {
            GetBindingsResponse response = client.sendRequest(path, request, GetBindingsResponse.class);
            handleResponseCode(response.responseCode());
            return response;
        } catch (Exception e) {
            throw new BaseException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * Activate a card binding.
     */
    public ActivateBindingResponse activateBinding(ActivateBindingRequest request) {
        String path = client.getConfig().getActivateBindingPath();
        try {
            ActivateBindingResponse response = client.sendRequest(path, request, ActivateBindingResponse.class);
            handleResponseCode(response.responseCode());
            return response;
        } catch (Exception e) {
            throw new BaseException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * Deactivate a card binding.
     */
    public DeactivateBindingResponse deactivateBinding(DeactivateBindingRequest request) {
        String path = client.getConfig().getDeactivateBindingPath();
        try {
            DeactivateBindingResponse response = client.sendRequest(path, request, DeactivateBindingResponse.class);
            handleResponseCode(response.responseCode());
            return response;
        } catch (Exception e) {
            throw new BaseException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * Utility method to handle response codes.
     */
    private void handleResponseCode(String responseCode) {
        if (!"00".equals(responseCode)) {
            throw new BaseException(ErrorCode.getErrorCodeByCode(responseCode));
        }
    }
}