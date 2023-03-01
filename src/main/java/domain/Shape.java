package domain;

public enum Shape {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아"),
    CLOVER("클로버");

    private final String shape;

    Shape(String shape) {
        this.shape = shape;
    }
}
