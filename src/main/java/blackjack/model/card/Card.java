package blackjack.model.card;

import java.util.Objects;

public final class Card {

    private final Suit suit;
    private final CardValue cardValue;

    Card(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    public boolean isAce() {
        return cardValue == CardValue.ACE;
    }

    public String getDisplayLabel() {
        return cardValue.getLabel() + suit.getLabel();
    }

    public CardValue getCardValue() {
        return cardValue;
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
        return suit == card.suit
                && cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, cardValue);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", cardValue=" + cardValue +
                '}';
    }
}
