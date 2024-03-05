package blackjack.domain;

public enum CardShape {

    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버"),
    SPADE("스페이드");

    private final String name;

    CardShape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
