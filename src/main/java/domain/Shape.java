package domain;

public enum Shape {
    HEART("하트"), DIAMOND("다이아몬드"), CLOVER("클로버"), SPADE("스페이드");

    private final String shapeName;

    Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }
}
