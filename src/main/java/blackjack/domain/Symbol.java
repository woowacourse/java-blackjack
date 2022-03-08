package blackjack.domain;

public enum Symbol {
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    HEART("하트");

    private final String name;

    Symbol(String name) {
        this.name = name;
    }
}
