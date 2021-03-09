package blackjack.domain.card;

public enum CardSuit {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String type;

    CardSuit(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
