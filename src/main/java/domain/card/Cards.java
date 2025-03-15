package domain.card;

import java.util.ArrayList;
import java.util.List;

public record Cards(List<Card> cards) {

    private static final int BLACKJACK_SCORE = 21;
    private static final int DEALER_DRAW_LIMIT = 16;
    private static final int ACE_VALUE_DIFFERENCE = 10;

    public int calculateTotalRank() {
        int total = getTotalOfAllCardsRank();
        int aceCount = getAceCount();

        while (total > BLACKJACK_SCORE && aceCount > 0) {
            total -= ACE_VALUE_DIFFERENCE;
            aceCount--;
        }

        return total;
    }

    private int getTotalOfAllCardsRank() {
        return cards.stream()
                .mapToInt(card -> card.rank().getNumericValue())
                .sum();
    }

    private int getAceCount() {
        return Math.toIntExact(cards.stream()
                .filter(Card::isAce)
                .count());
    }

    public Cards addCards(List<Card> providedCards) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.addAll(providedCards);
        return new Cards(newCards);
    }

    public boolean isBlackjackScoreExceeded() {
        return calculateTotalRank() > BLACKJACK_SCORE;
    }

    public boolean isDealerDrawLimitExceeded() {
        return calculateTotalRank() > DEALER_DRAW_LIMIT;
    }

    public boolean equalToBlackjackScore() {
        return calculateTotalRank() == BLACKJACK_SCORE;
    }
}
