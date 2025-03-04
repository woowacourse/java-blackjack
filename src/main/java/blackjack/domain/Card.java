package blackjack.domain;

public class Card {
    private final CardNumber number;
    private final CardShape shape;

    public Card(CardNumber number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public int getNumber() {
        return number.getValue();
    }

    public CardShape getShape() {
        return shape;
    }
}
