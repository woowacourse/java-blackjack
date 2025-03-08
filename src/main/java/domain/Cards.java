package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int DEALER_DRAW_LIMIT = 16;
    private static final int ACE_VALUE_DIFFERENCE = 10;

    private final List<Card> cards;
    private final int totalRankSum;

    public Cards(List<Card> cards) {
        this.cards = cards;
        this.totalRankSum = calculateTotalRank();
    }

    public int calculateTotalRank() {
        int total = cards.stream()
                .mapToInt(card -> card.getRank().getNumericValue())
                .sum();
        
        if (total > BLACKJACK_SCORE && isContainsAce()) {
            return total - ACE_VALUE_DIFFERENCE;
        }

        return total;
    }

    private boolean isContainsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public Cards addCards(List<Card> providedCards) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.addAll(providedCards);
        return new Cards(newCards);
    }

    public boolean checkExceedTwentyOne() {
        return totalRankSum > BLACKJACK_SCORE;
    }

    public boolean checkExceedSixteen() {
        return totalRankSum > DEALER_DRAW_LIMIT;
    }

    public Card getInitialCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateDifferenceFromTwentyOne() {
        return Math.abs(totalRankSum - BLACKJACK_SCORE);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return totalRankSum == cards1.totalRankSum && Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, totalRankSum);
    }
}
