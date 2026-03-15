package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private static final Integer MAXIMUM_TOTAL_SCORE = 21;
    private static final Integer MINIMUM_DEALER_SCORE = 16;

    private final List<Card> cards;
    private int handTotalScore;

    public Hand() {
        cards = new ArrayList<>();
        handTotalScore = 0;
    }

    public void saveCard(Card card) {
        cards.add(card);
    }

    public String getOneCardDisplay() {
        return cards.getFirst().getDisplayName();
    }

    public String getCardsDisplay() {
        return cards.stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }

    public void calculateHandScore() {
        int initialScore = calculateInitialScore();
        int aceCount = countAces();
        this.handTotalScore = applyAceRule(initialScore, aceCount);
    }

    private int calculateInitialScore() {
        return cards.stream()
                .mapToInt(Card::getCardScore)
                .sum();
    }

    private int countAces() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    private int applyAceRule(int score, int aceCount) {
        while ((score > MAXIMUM_TOTAL_SCORE) && (aceCount > 0)) {
            score -= 10;
            aceCount--;
        }
        return score;
    }

    public Boolean determineDealerDealMore() {
        return handTotalScore <= MINIMUM_DEALER_SCORE;
    }

    public int getHandTotalScore() {
        return handTotalScore;
    }

    public boolean isBlackjack() {
        return (getHandTotalScore() == MAXIMUM_TOTAL_SCORE) && (cards.size() == 2);
    }

    public boolean isBust() {
        return this.handTotalScore > MAXIMUM_TOTAL_SCORE;
    }
}
