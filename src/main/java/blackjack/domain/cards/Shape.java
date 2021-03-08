package blackjack.domain.cards;

public enum Shape {
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    SPADE("스페이드");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
