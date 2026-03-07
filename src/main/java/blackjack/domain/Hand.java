package blackjack.domain;

import java.util.List;

public class Hand {

    private static final int BURST_THRESHOLD = 21;
    private static final int ACE_SCORE_DIFFERENCE = 10;

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

        return adjustTotalScoreByAce(totalScore);
    }

    public long countAce() {
        return cards.stream()
            .filter(Card::isAce)
            .count();
    }

    public int adjustTotalScoreByAce(int totalScore) {
        long aceCount = countAce();
        int updatedScore = totalScore;
        while (aceCount > 0 && updatedScore > BURST_THRESHOLD) {
            updatedScore -= ACE_SCORE_DIFFERENCE;
            aceCount--;
        }

        return updatedScore;
    }

    public boolean isBurst() {
        return calculateScore() > BURST_THRESHOLD;
    }

    public List<String> getCardNames(int startInclusive) {
        return cards.subList(startInclusive, cards.size()).stream()
            .map(Card::getName)
            .toList();
    }
}
