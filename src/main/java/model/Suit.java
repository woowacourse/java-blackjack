package model;

public enum Suit {
    CLUB("♣️"),
    SPADE("♠️"),
    HEART("♥️"),
    DIAMOND("♦️");

    private final String shape;

    Suit(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
