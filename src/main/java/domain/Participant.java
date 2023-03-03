package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {
    private static final int BUST_LIMIT = 21;
    private static final int ACE_GAP = 10;
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
        while (score > BUST_LIMIT && aceCount > 0) {
            score -= ACE_GAP;
            aceCount--;
        }

        return score;
    }

    public boolean isBusted() {
        return calculateScore() > BUST_LIMIT;
    }

    public int cardSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
