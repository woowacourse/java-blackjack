package domain.card;

import java.util.Objects;

public class Card {
    private final CardValue cardValue;
    private final CardSuit cardSuit;

    public Card(CardValue cardValue, CardSuit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public CardValue getCardNumber() {
        return cardValue;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return cardValue == card.cardValue && cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue, cardSuit);
    }
}
