package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int DEALER_DRAW_LIMIT = 16;
    private static final int ACE_VALUE_DIFFERENCE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

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
                .mapToInt(card -> card.getRank().getNumericValue())
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

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
