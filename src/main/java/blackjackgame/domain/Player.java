package blackjackgame.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    List<Card> cards;

    public int getScore() {
        int totalScore = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            hasAce |= card.getValue().equals(CardValue.ACE);
            totalScore += card.getScore();
        }

        if (hasAce && totalScore <= 11) {
            totalScore += 10;
        }
        return totalScore;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Collection<Object> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract boolean isPick();
}
