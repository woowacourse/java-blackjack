package domain.card;

public enum Shape {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    ;

    private final String shape;

    Shape(String shape) {
        this.shape = shape;
    }

    public String getName() {
        return shape;
    }
}
