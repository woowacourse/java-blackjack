package blackjack.domain;

import blackjack.common.CardRank;
import blackjack.common.CardSuit;
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

    public List<Integer> getRankValues() {
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

    public String toCardName() {
        return cardRank.getDescription() + cardSuit.getDescription();
    }

}
