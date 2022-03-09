package blackjack.domain;

public enum Suit {

    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    ;

    private final String name;

    private Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
