package blackjack.domain.card;

public class Card {
    private final CardShape shape;
    private final CardNumber number;

    private Card(CardShape cardShape, CardNumber cardNumber) {
        this.shape = cardShape;
        this.number = cardNumber;
    }

    public static Card of(CardShape shape, CardNumber number) {
        return new Card(shape, number);
    }

    public boolean isAce() {
        return number.isAce();
    }

    public CardShape getCardShape() {
        return shape;
    }

    public CardNumber getCardNumber() {
        return number;
    }
}
