package blackjack.domain;

public enum CardPattern {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEARD("하트"),
    CLOVER("클로버"),
    ;

    private final String name;

    CardPattern(final String name) {
        this.name = name;
    }
}
