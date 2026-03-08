package blackjack.model;

import java.util.List;

public class Score {
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

    public boolean isSame(Score otherScore) {
        return score == otherScore.score;
    }

    public boolean isLess(Score otherScore) {
        return score < otherScore.score;
    }

    public boolean isDealerHitScore() {
        return score <= 16;
    }

    public boolean isPlayerHitScore() {
        return score < 21;
    }

    public boolean isBurst() {
        return score > 21;
    }

    public void add(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    private int aceTranslate(int score, long aceCount) {
        while (score <= 11 && aceCount > 0) {
            score += 10;
            aceCount--;
        }
        return score;
    }
}
