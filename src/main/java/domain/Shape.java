package domain;

public enum Shape {

    SPADE("스페이드"),
    DIAMONDS("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private final String shape;

    Shape(String shape) {
        this.shape = shape;
    }
}
