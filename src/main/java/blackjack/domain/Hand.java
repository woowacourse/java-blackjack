package blackjack.domain;

import java.util.List;

public class Hand {
    private static final int BURST_THRESHOLD = 21;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
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

    public boolean isBurst() {
        return calculateScore() > BURST_THRESHOLD;
    }

    public List<String> getCardNames(int startInclusive) {
        return cards.subList(startInclusive, cards.size()).stream()
            .map(Card::toString)
            .toList();
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculateScore() == BURST_THRESHOLD;
    }

    private int handleAce(int totalScore) {
        long aceCount = countAce();
        int updatedScore = totalScore;
        int aceScoreDifference = 10;
        while (aceCount > 0 && updatedScore > BURST_THRESHOLD) {
            updatedScore -= aceScoreDifference;
            aceCount--;
        }

        return updatedScore;
    }
}
