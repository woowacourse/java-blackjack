package blackjack.model;

public class AceCard {

    private final CardShape shape;

    public AceCard(CardShape shape) {
        this.shape = shape;
    }

    public CardShape getShape() {
        return shape;
    }
}
