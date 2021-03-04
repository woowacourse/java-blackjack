package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final int MAXIMUM_TO_ACE_IS_ELEVEN = 11;
    private static final int MAKING_ACE_ELEVEN = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addNewCard(Card card) {
        cards.add(card);
    }

    public int calculateJudgingPoint() {
        int point = 0;
        for (Card card : cards) {
            point = card.addPoint(point);
        }
        return point;
    }

    public int calculateMaximumPoint() {
        int point = 0;
        boolean havingAce = false;
        for (Card card : cards) {
            point = card.addPoint(point);
            if (card.isAce()) {
                havingAce = true;
            }
        }
        if (point <= MAXIMUM_TO_ACE_IS_ELEVEN && havingAce) {
            point += MAKING_ACE_ELEVEN;
        }
        return point;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
