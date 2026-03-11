package blackjack.domain;

import java.util.List;

public class Hand {

    private static final int BURST_THRESHOLD = 21;
    private static final int ACE_SCORE_DIFFERENCE = 10;
    private static final int BLACKJACK_CARDS_SIZE = 2;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        final int totalScore = cards.stream()
            .mapToInt(Card::toScore)
            .sum();

        return adjustTotalScoreByAce(totalScore);
    }

    public long countAce() {
        return cards.stream()
            .filter(Card::isAce)
            .count();
    }

    public int adjustTotalScoreByAce(final int totalScore) {
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

    public boolean isBlackjack() {
        if (calculateScore() == BURST_THRESHOLD && cards.size() == BLACKJACK_CARDS_SIZE) {
            return true;
        }
        return false;
    }

    public List<String> getCardNames(final int startInclusive) {
        return cards.subList(startInclusive, cards.size()).stream()
            .map(Card::getName)
            .toList();
    }
}
