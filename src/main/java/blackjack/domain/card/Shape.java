package blackjack.domain.card;

public enum Shape {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");
    private final String name;

    Shape(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
