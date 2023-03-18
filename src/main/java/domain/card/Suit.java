package domain.card;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아"),
    CLOVER("클로버");

    private final String shape;

    Suit(final String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
