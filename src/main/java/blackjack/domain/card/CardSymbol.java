package blackjack.domain.card;

public enum CardSymbol {

    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String displayName;

    CardSymbol(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
