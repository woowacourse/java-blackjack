package blackjack.domain.card;

public enum Shape {
    SPACE("스페이스"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private String shape;

    Shape(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return this.shape;
    }
}
