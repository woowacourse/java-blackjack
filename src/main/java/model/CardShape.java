package model;

public enum CardShape {
    DIAMOND(0, "다이아몬드"),
    HEART(1, "하트"),
    CLOVER(2, "클로버"),
    SPADE(3, "스페이드");

    private final int index;
    private final String shape;

    CardShape(int index, String shape) {
        this.index = index;
        this.shape = shape;
    }

    public static CardShape from(int index) {
        for (CardShape shape : values()) {
            if (shape.index == index) {
                return shape;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 카드 모양입니다.");
    }

    public String getShape() {
        return shape;
    }
}
