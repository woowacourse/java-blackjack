package blackjack.domain.card;

public enum CardShape {
    DIAMOND("다이아몬드", 0),
    HEART("하트", 1),
    SPADE("스페이드", 2),
    CLUB("클로버", 3);

    private final String shape;
    private final int value;

    CardShape(String shape, int value) {
        this.shape = shape;
        this.value = value;
    }

    public String getShape() {
        return shape;
    }

    public int getValue() {
        return value;
    }
}