package am.programming.services;


import am.programming.client.AmeriaBankClient;
import am.programming.exceptions.BaseException;
import am.programming.models.requests.*;
import am.programming.models.response.*;
import am.programming.utils.logging.LogExecution;

import static am.programming.exceptions.ErrorCode.*;

@LogExecution
public class PaymentService {
    private final AmeriaBankClient client;

    public PaymentService(AmeriaBankClient client) {
        this.client = client;
    }

    public InitPaymentResponse initPayment(InitPaymentRequest request) {
        try {
            String path = client.getConfig().getInitPaymentPath();
            return client.sendRequest(path, request, InitPaymentResponse.class);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(SYSTEM_ERROR);
        }
    }

    public PaymentDetailsResponse getPaymentDetails(PaymentDetailsRequest request) {
        try {
            String path = client.getConfig().getPaymentDetailsPath();
            return client.sendRequest(path, request, PaymentDetailsResponse.class);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(SYSTEM_ERROR);
        }
    }

    public ConfirmPaymentResponse confirmPayment(ConfirmPaymentRequest request) {
        try {
            String path = client.getConfig().getConfirmPaymentPath();
            return client.sendRequest(path, request, ConfirmPaymentResponse.class);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(SYSTEM_ERROR);
        }
    }

    public CancelPaymentResponse cancelPayment(CancelPaymentRequest request) {
        try {
            String path = client.getConfig().getCancelPaymentPath();
            return client.sendRequest(path, request, CancelPaymentResponse.class);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(SYSTEM_ERROR);
        }
    }

    public RefundPaymentResponse refundPayment(RefundPaymentRequest request) {
        try {
            String path = client.getConfig().getRefundPaymentPath();
            return client.sendRequest(path, request, RefundPaymentResponse.class);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(SYSTEM_ERROR);
        }
    }
}
