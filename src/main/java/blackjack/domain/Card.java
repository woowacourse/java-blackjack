package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Card {

    private final CardSuit cardSuit;
    private final CardRank cardRank;

    public Card(CardSuit cardSuit, CardRank cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    public List<Integer> getValue() {
        return Collections.unmodifiableList(cardRank.getValues());
    }

    @Override
    public boolean equals(Object o) {
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

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public CardRank getCardRank() {
        return cardRank;
    }
}
