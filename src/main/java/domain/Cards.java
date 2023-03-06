package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
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

    public Card getFirstCard() {
        return cards.get(0);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
