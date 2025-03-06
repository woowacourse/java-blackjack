package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
    
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
        
        if (total > 21 && isContainsAce()) {
            return total - 10;
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
        return totalNumberSum > 21;
    }

    public boolean checkExceedSixteen() {
        return totalNumberSum > 16;
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
        return totalNumberSum == cards1.totalNumberSum && Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, totalNumberSum);
    }
}
