package blackjack.model;

public class Card {

    private final CardShape shape;

    public Card(CardShape shape) {
        this.shape = shape;
    }

    public CardShape getShape() {
        return shape;
    }
}
