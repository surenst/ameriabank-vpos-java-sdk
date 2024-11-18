package am.programming.config;

public class SdkConfig {
    private final String baseUrl;
    private final String makeBindingPaymentPath;
    private final String getBindingsPath;
    private final String activateBindingPath;
    private final String deactivateBindingPath;
    private final String initPaymentPath;
    private final String confirmPaymentPath;
    private final String cancelPaymentPath;
    private final String paymentDetailsPath;
    private final String refundPaymentPath;
    private final String username;
    private final String password;

    private SdkConfig(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.makeBindingPaymentPath = builder.makeBindingPaymentPath;
        this.getBindingsPath = builder.getBindingsPath;
        this.activateBindingPath = builder.activateBindingPath;
        this.deactivateBindingPath = builder.deactivateBindingPath;
        this.initPaymentPath = builder.initPaymentPath;
        this.confirmPaymentPath = builder.confirmPaymentPath;
        this.cancelPaymentPath = builder.cancelPaymentPath;
        this.paymentDetailsPath = builder.paymentDetailsPath;
        this.refundPaymentPath = builder.refundPaymentPath;
        this.username = builder.username;
        this.password = builder.password;
    }

    // Static method to get the Builder instance
    public static Builder builder() {
        return new Builder();
    }

    public String getBaseUrl() { return baseUrl; }
    public String getMakeBindingPaymentPath() { return makeBindingPaymentPath; }
    public String getGetBindingsPath() { return getBindingsPath; }
    public String getActivateBindingPath() { return activateBindingPath; }
    public String getDeactivateBindingPath() { return deactivateBindingPath; }
    public String getInitPaymentPath() { return initPaymentPath; }
    public String getConfirmPaymentPath() { return confirmPaymentPath; }
    public String getCancelPaymentPath() { return cancelPaymentPath; }
    public String getPaymentDetailsPath() { return paymentDetailsPath; }
    public String getRefundPaymentPath() { return refundPaymentPath; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public static class Builder {
        private String baseUrl;
        private String makeBindingPaymentPath;
        private String getBindingsPath;
        private String activateBindingPath;
        private String deactivateBindingPath;
        private String initPaymentPath;
        private String confirmPaymentPath;
        private String cancelPaymentPath;
        private String paymentDetailsPath;
        private String refundPaymentPath;
        private String username;
        private String password;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder makeBindingPaymentPath(String path) {
            this.makeBindingPaymentPath = path;
            return this;
        }

        public Builder getBindingsPath(String path) {
            this.getBindingsPath = path;
            return this;
        }

        public Builder activateBindingPath(String path) {
            this.activateBindingPath = path;
            return this;
        }

        public Builder deactivateBindingPath(String path) {
            this.deactivateBindingPath = path;
            return this;
        }

        public Builder initPaymentPath(String path) {
            this.initPaymentPath = path;
            return this;
        }

        public Builder confirmPaymentPath(String path) {
            this.confirmPaymentPath = path;
            return this;
        }

        public Builder cancelPaymentPath(String path) {
            this.cancelPaymentPath = path;
            return this;
        }

        public Builder paymentDetailsPath(String path) {
            this.paymentDetailsPath = path;
            return this;
        }

        public Builder refundPaymentPath(String path) {
            this.refundPaymentPath = path;
            return this;
        }

        public SdkConfig build() {
            return new SdkConfig(this);
        }
    }

    @Override
    public String toString() {
        return "SdkConfig{" +
                "baseUrl='" + baseUrl + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
