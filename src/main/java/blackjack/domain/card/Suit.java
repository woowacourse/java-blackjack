package blackjack.domain.card;

public enum Suit {
    SPACE("스페이스"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String shape;

    Suit(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return this.shape;
    }
}
