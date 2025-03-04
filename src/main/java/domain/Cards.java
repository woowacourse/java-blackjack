package domain;

import java.util.List;

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
}
