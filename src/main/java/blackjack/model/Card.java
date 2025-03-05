package blackjack.model;

public abstract class Card {

    private final CardShape shape;

    public Card(CardShape shape) {
        this.shape = shape;
    }

    public CardShape getShape() {
        return shape;
    }

    public abstract int getPoint();
}
