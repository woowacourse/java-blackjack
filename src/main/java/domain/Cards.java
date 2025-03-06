package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {

    public static final int BLACKJACK_SCORE = 21;
    public static final int DEALER_DRAW_LIMIT = 16;
    public static final int ACE_VALUE_DIFFERENCE = 10;

    private final List<Card> cards;
    private final int totalNumberSum;

    public Cards(List<Card> cards) {
        this.cards = cards;
        this.totalNumberSum = calculateTotalCardNumber();
    }

    public int calculateTotalCardNumber() {
        int total = cards.stream()
                .mapToInt(card -> card.getNumber().getNumericValue())
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
        return totalNumberSum > BLACKJACK_SCORE;
    }

    public boolean checkExceedSixteen() {
        return totalNumberSum > DEALER_DRAW_LIMIT;
    }

    public Card getInitialCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateDifferenceFromTwentyOne() {
        return Math.abs(totalNumberSum - BLACKJACK_SCORE);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return totalNumberSum == cards1.totalNumberSum && Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, totalNumberSum);
    }
}
