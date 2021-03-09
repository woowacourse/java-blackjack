package blackjack.domain.card;

public enum CardType {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String type;

    CardType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
