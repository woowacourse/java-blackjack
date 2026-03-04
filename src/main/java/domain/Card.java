package domain;

import java.util.Objects;

public class Card {
    private CardNumber cardNumber;
    private CardShape cardShape;

    public Card(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public int getScore() {
        if(cardNumber == CardNumber.ACE){
            return 1;
        }
        if(cardNumber == CardNumber.JACK || cardNumber == CardNumber.QUEEN || cardNumber == CardNumber.KING) {
            return 10;
        }
        return Integer.parseInt(cardNumber.getNumber());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return cardNumber == card.cardNumber && cardShape == card.cardShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardShape);
    }
}
