package domain;

import java.util.Objects;

public class Card {
    private final CardNumber cardNumber;
    private final CardSuit cardSuit;

    public Card(CardNumber cardNumber, CardSuit cardSuit) {
        this.cardNumber = cardNumber;
        this.cardSuit = cardSuit;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public int getScore() {
        if (cardNumber == CardNumber.ACE) {
            return 1;
        }
        if (cardNumber == CardNumber.JACK || cardNumber == CardNumber.QUEEN || cardNumber == CardNumber.KING) {
            return 10;
        }
        return Integer.parseInt(cardNumber.getNumber());
    }

    @Override
    public String toString() {
        return cardNumber.getNumber() + cardSuit.getShape();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return cardNumber == card.cardNumber && cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardSuit);
    }
}
