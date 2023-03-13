package blackjack.domain.card;

public enum Suit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String shape;

    Suit(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
