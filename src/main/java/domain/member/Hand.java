package domain.member;

import domain.card.Card;
import domain.exception.DuplicatedException;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK = 21;
    private static final int SOFT_HAND_VALUE = 10;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public List<Card> cards() {
        return cards;
    }

    public void appendCard(Card card) {
        validateDuplicate(card);
        cards.add(card);
    }

    public int calculateTotalValue() {
        int totalSum = cards.stream()
                .mapToInt(Card::number)
                .sum();
        if (hasAce() && totalSum + SOFT_HAND_VALUE <= BLACKJACK) {
            return totalSum + 10;
        }
        return totalSum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private void validateDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new DuplicatedException();
        }
    }
}
