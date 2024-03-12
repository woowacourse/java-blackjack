package blackjack.domain.card;

public enum CardShape {
    CLOVER("클로버"),
    DIA("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트");

    private final String shape;

    CardShape(final String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
