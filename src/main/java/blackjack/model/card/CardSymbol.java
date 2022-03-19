package blackjack.model.card;

public enum CardSymbol {

    HEART("♥"),
    DIAMOND("♦"),
    CLOVER("♣"),
    SPADE("♠"),
    ;

    private final String value;

    CardSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
