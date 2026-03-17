package domain;

import static domain.BlackjackRule.ACE_SCORE_DIFFERENCE;
import static domain.BlackjackRule.BLACKJACK_CARD_COUNT;
import static domain.BlackjackRule.BLACKJACK_TARGET_SCORE;
import static domain.BlackjackRule.DEALER_MAX_HIT_SCORE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
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
        while ((score > BLACKJACK_TARGET_SCORE) && (aceCount > 0)) {
            score -= ACE_SCORE_DIFFERENCE;
            aceCount--;
        }
        return score;
    }

    public Boolean determineDealerDealMore() {
        return handTotalScore <= DEALER_MAX_HIT_SCORE;
    }

    public int getHandTotalScore() {
        return handTotalScore;
    }

    public boolean isBlackjack() {
        return (getHandTotalScore() == BLACKJACK_TARGET_SCORE) && (cards.size() == BLACKJACK_CARD_COUNT);
    }

    public boolean isBust() {
        return this.handTotalScore > BLACKJACK_TARGET_SCORE;
    }
}
