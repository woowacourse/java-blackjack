package blackjack.domain;

import java.util.List;

public class Score {
    public static final int BLACKJACK_NUMBER = 21;

    private int score;

    public Score() {
        this.score = 0;
    }

    public void calculateAll(List<Card> cards) {
        long aceCount = cards.stream()
                .filter(card -> Number.ACE.equals(card.getNumber()))
                .count();

        this.score = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        if (aceCount != 0) {
            score = aceTranslate(score, aceCount);
        }
    }

    private int aceTranslate(int score, long aceCount) {
        while (score <= 11 && aceCount > 0) {
            score += 10;
            aceCount--;
        }
        return score;
    }

    public boolean isBurst() {
        return score > BLACKJACK_NUMBER;
    }

    public void add(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public boolean isLess(int score) {
        return this.score < score;
    }

    public boolean isBlackjack() {
        return score == BLACKJACK_NUMBER;
    }
}
