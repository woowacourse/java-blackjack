package domain;

public enum CardSuit {
    HEART("하트"),
    SPADE("스페이드"),
    CLUB("클로버"),
    DIAMOND("다이아몬드");

    private final String shape;

    CardSuit(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
