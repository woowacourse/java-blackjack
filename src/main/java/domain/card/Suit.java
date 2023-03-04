package domain.card;

public enum Suit {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드");

    private final String alias;

    Suit(final String alias) {
        this.alias = alias;
    }
}
