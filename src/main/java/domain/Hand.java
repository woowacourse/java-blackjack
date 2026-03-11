package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int totalScore = 0;
        int aceCount = 0;
        for (Card card : cards) {
            totalScore += card.getScore();
            if (card.isAce()) {
                aceCount++;
            }
        }

        while (canLowerAceScore(totalScore, aceCount)) {
            totalScore -= 10;
            aceCount--;
        }

        return new Score(totalScore);
    }

    public int size() {
        return cards.size();
    }

    private boolean canLowerAceScore(int score, int aceCount) {
        return score > 21 && aceCount > 0;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
