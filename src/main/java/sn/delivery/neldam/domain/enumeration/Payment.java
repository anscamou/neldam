package sn.delivery.neldam.domain.enumeration;

/**
 * The Payment enumeration.
 */
public enum Payment {
    CREDIT_CARD("card"),
    CASH("cash"),
    ORANGE_MONEY("om"),
    WAVE("wave"),
    FREE_MONEY("fm"),
    OTHERS("others");

    private final String value;


    Payment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
