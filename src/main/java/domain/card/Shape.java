package domain.card;

public enum Shape {

    SPADE("스페이드"), DIAMONDS("다이아몬드"), HEART("하트"), CLUB("클로버");

    private final String value;

    Shape(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Shape{" + "shape='" + value + '\'' + '}';
    }
}
