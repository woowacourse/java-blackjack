package domain;

public enum Suit {
    CLUB("클로버"),
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String shape;

    Suit(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
