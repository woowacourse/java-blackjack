package blackjack.domain.card.property;

public class CardProperty {
    private final CardShape shape;
    private final CardNumber number;

    public CardProperty(CardShape shape, CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public boolean isAce() {
        return number.isAce();
    }

    public CardShape getShape() {
        return shape;
    }

    public CardNumber getNumber() {
        return number;
    }
}
