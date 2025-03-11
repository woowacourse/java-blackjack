package blackjack.domain.card;

public final class Card {

    private final CardShape shape;
    private final CardValue value;

    public Card(CardShape shape, CardValue value) {
        this.shape = shape;
        this.value = value;
    }

    public String getShape() {
        return shape.getDescription();
    }

    public CardValue getValue() {
        return value;
    }

    public int getPoint() {
        return value.getPoint();
    }
}
