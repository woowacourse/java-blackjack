package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private static final Integer MAXIMUM_TOTAL_SCORE = 21;
    private static final Integer MINIMUM_DEALER_SCORE = 16;

    private final List<Card> cards;
    private int handTotalScore;
    private boolean hasAce;

    public Hand() {
        cards = new ArrayList<>();
        handTotalScore = 0;
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
        this.handTotalScore = 0;
        for (Card card : cards) {
            handTotalScore += card.getCardScore();
        }

        if (hasAce && handTotalScore > MAXIMUM_TOTAL_SCORE) {
            handTotalScore -= 10;
        }
    }

    public Boolean determineDealerDealMore() {
        if (handTotalScore <= MINIMUM_DEALER_SCORE) {
            return true;
        }
        return false;
    }

    public String getFinalDisplay() {
        return " - 결과: " + handTotalScore;
    }

    public int getHandTotalScore() {
        return handTotalScore;
    }
}
