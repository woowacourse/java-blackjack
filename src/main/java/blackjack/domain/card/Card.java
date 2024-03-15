package blackjack.domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardShape cardShape;

    public Card(final CardNumber cardNumber, final CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public boolean isAceCard() {
        return cardNumber == CardNumber.ACE;
    }

    public CardNumber getNumber() {
        return cardNumber;
    }

    public CardShape getShape() {
        return cardShape;
    }
}
