package domain;

import java.util.Objects;

public class Card {

    private int number;
    private CardType cardType;

    public Card(int number, CardType cardType) {
        this.number = number;
        this.cardType = cardType;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return number == card.number && cardType == card.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, cardType);
    }
}
