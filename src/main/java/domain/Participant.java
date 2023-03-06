package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private final List<Card> cards = new ArrayList<>();

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int countOfAce = countAce();
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        return applyAce(score, countOfAce);
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int applyAce(int score, int aceCount) {
        while (score > BlackjackRule.BUST_LIMIT.getValue() && aceCount > 0) {
            score -= BlackjackRule.ACE_GAP.getValue();
            aceCount--;
        }

        return score;
    }

    public boolean isBusted() {
        return calculateScore() > BlackjackRule.BUST_LIMIT.getValue();
    }

    public int cardSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
