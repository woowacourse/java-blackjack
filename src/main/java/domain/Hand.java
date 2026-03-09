package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private static final Integer WINNING_SCORE_BOUNDARY = 21;
    private static final Integer ACE_SUBTRACTION_POINT = 10;
    private static final Integer DEALER_DEAL_AGAIN_BOUNDARY = 16;

    private final List<Card> cards;
    private int totalScore;
    private boolean hasAce;

    public Hand() {
        cards = new ArrayList<>();
        totalScore = 0;
        hasAce = false;
    }

    public void saveCard(Card card) {
        cards.add(card);
        if (card.isAceCard()) {
            hasAce = true;
        }
    }

    public String getCardsDisplay() {
        return cards.stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }

    public void calculateHandScore() {
        this.totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getCardScore();
        }

        if (hasAce && totalScore > WINNING_SCORE_BOUNDARY) {
            totalScore -= ACE_SUBTRACTION_POINT;
        }
    }

    public Boolean determineDealerDealMore() {
        if (totalScore <= DEALER_DEAL_AGAIN_BOUNDARY) {
            return true;
        }
        return false;
    }

    public String getFinalDisplay() {
        String finalDisplay = " - 결과: " + totalScore;
        return finalDisplay;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
