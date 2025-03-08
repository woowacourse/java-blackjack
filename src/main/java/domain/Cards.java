package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_VALUE_DIFFERENCE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
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

    public boolean checkBurst() {
        return calculateTotalCardNumber() > BLACKJACK_SCORE;
    }

    public int calculateDifferenceFromBurst() {
        return Math.abs(calculateTotalCardNumber() - BLACKJACK_SCORE);
    }

    public Card getInitialCard() {
        return cards.getFirst();
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
        return Objects.hashCode(cards);
    }
}
