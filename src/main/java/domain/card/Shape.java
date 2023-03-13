package domain.card;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/04
 */
public enum Shape {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String shape;

    Shape(final String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return this.shape;
    }
}
