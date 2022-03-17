package blackjack.domain.cards.card.denomination;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String value;

    Suit(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
