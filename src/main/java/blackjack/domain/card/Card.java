package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardRank cardRank;
    private final CardSuit cardSuit;

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public boolean isAce() {
        return cardRank.isAce();
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
        return cardSuit == card.cardSuit && cardRank == card.cardRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSuit, cardRank);
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }
}
