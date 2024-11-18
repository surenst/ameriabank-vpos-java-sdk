package am.programming.models;

public enum Currency {
    AMD("051"),
    EUR("978"),
    USD("840"),
    RUB("643");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Currency fromCode(String code) {
        for (Currency currency : Currency.values()) {
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        return null;
    }
}
