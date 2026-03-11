package domain;

import java.util.Objects;

public class Card {
    private final CardSuit cardSuit;
    private final CardRank cardRank;

    public Card(final CardSuit cardSuit, final CardRank cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    public String getCardDescription() {
        return cardRank.getDescription() + cardSuit.getDescription();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return cardSuit == card.cardSuit && cardRank == card.cardRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSuit, cardRank);
    }

    public CardRank getCardRank() {
        return cardRank;
    }
}
