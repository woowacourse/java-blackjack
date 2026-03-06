package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private List<Card> cards;
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

        if (hasAce && handTotalScore > 21) {
            handTotalScore -= 10;
        }
    }

    public Boolean determineDealerDealMore() {
        if (handTotalScore <= 16) {
            return true;
        }
        return false;
    }

    public String getFinalDisplay() {
        String finalDisplay = " - 결과: " + handTotalScore;
        return finalDisplay;
    }
}
