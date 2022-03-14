package blackjack.domain.card;

public enum CardSymbol {

    HEART("♥️"),
    SPADE("♣️"),
    DIAMOND("♦️"),
    CLOVER("♠️");

    private final String displayName;

    CardSymbol(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
