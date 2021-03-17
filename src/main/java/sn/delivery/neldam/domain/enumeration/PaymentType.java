package sn.delivery.neldam.domain.enumeration;

/**
 * The PaymentType enumeration.
 */
public enum PaymentType {
    CREDIT_CARD("card"),
    CASH("cash"),
    ORANGE_MONEY("om"),
    WAVE("wave"),
    FREE_MONEY("fm"),
    OTHERS("others");

    private final String value;


    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
