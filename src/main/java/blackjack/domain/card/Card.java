package blackjack.domain.card;

public class Card {
    private final CardShape shape;
    private final CardNumber number;

    private Card(CardShape cardShape, CardNumber cardNumber) {
        this.shape = cardShape;
        this.number = cardNumber;
    }

    private Card(Card card) {
        this.shape = card.shape;
        this.number = card.number;
    }

    public static Card of(CardShape shape, CardNumber number) {
        return new Card(shape, number);
    }

    public Card copy() {
        return new Card(this);
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
