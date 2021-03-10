package blackjack.domain.card;

public enum Shape {
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
