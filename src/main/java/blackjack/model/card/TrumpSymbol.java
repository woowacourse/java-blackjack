package blackjack.model.card;

public enum TrumpSymbol {
    HEART("♥"),
    DIAMOND("♦"),
    CLOVER("♣"),
    SPADE("♠"),
    ;

    private final String value;

    TrumpSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
