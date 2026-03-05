package blackjack.domain;

import java.util.List;

public class Hand {
    private static final int BURST_THRESHOLD = 21;

    private final List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        int totalScore = cards.stream()
            .mapToInt(Card::toScore)
            .sum();

        return handleAce(totalScore);
    }

    public long countAce() {
        return cards.stream()
            .filter(Card::isAce)
            .count();
    }

    public int handleAce(int totalScore) {
        long aceCount = countAce();
        int updatedScore = totalScore;
        while (aceCount > 0 && updatedScore > BURST_THRESHOLD) {
            updatedScore -= 10;
            aceCount--;
        }

        return updatedScore;
    }



    public boolean isBurst() {
        return calculateScore() > BURST_THRESHOLD;
    }
}
