package blackjack.domain;

public enum Pattern {
    HEART("하트"),
    SPADE("스페이드"),
    CLUB("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    Pattern(final String name) {
        this.name = name;
    }
}
