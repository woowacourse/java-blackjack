package card;

import java.util.Objects;

public class Card {
    private final Rank cardNumber;
    private final Suit suit;

    public Card(Rank cardNumber, Suit suit) {
        this.cardNumber = cardNumber;
        this.suit = suit;
    }

    public String getName() {
        return cardNumber.getLabel() + suit.getValue();
    }

    public int getScore() {
        return cardNumber.getValue();
    }

    public boolean isAce() {
        return cardNumber.getLabel().equals(Rank.ACE.getLabel());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardNumber == card.cardNumber && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, suit);
    }

    @Override
    public String toString() {
        return cardNumber.getLabel() + suit.getValue();
    }
}
