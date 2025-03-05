package blackjack.model;

public abstract class Card {

    private final CardShape shape;
    private final int point;

    public Card(CardShape shape, int point) {
        this.shape = shape;
        this.point = point;
    }

    public CardShape getShape() {
        return shape;
    }

    public abstract int getPoint();
}
