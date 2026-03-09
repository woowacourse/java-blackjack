package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private static final Integer WINNING_SCORE_BOUNDARY = 21;
    private static final Integer ACE_SUBTRACTION_POINT = 10;
    private static final Integer DEALER_DEAL_AGAIN_BOUNDARY = 16;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void saveCard(Card card) {
        cards.add(card);
    }

    public String getCardsDisplay() {
        return cards.stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }

    public Boolean determineDealerDealMore() {
        if (calculateTotalScore() <= DEALER_DEAL_AGAIN_BOUNDARY) {
            return true;
        }
        return false;
    }

    public String getFinalDisplay() {
        return " - 결과: " + calculateTotalScore();
    }

    public int calculateTotalScore() {
        int totalScore = cards.stream()
                .mapToInt(Card::getCardScore)
                .sum();

        if (totalScore > WINNING_SCORE_BOUNDARY) {
            totalScore = adjustContainAce(totalScore);
        }
        return totalScore;
    }

    private int adjustContainAce(int totalScore) {
        for (Card card : cards) {
            totalScore = adjustAce(totalScore, card);
        }
        return totalScore;
    }

    private int adjustAce(int totalScore, Card card) {
        if (totalScore <= WINNING_SCORE_BOUNDARY) {
            return totalScore;
        }
        if (card.isAceCard()) {
            return totalScore - ACE_SUBTRACTION_POINT;
        }
        return totalScore;
    }
}
