package blackjack.domain.card;

public enum CardShape {

    SPADE("♠"),
    HEART("♥"),
    CLOVER("♣"),
    DIAMOND("♦"),
    ;

    private final String shape;

    CardShape(final String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
