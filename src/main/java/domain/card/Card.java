package domain.card;

import java.util.Objects;

public class Card {
    private final CardScore cardScore;
    private final CardSuit cardSuit;

    public Card(CardScore cardScore, CardSuit cardSuit) {
        this.cardScore = cardScore;
        this.cardSuit = cardSuit;
    }

    public boolean isAce() {
        return this.getCardValue() == CardScore.ACE;
    }

    public CardScore getCardValue() {
        return cardScore;
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
        return cardScore == card.cardScore && cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardScore, cardSuit);
    }
}
