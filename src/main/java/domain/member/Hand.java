package domain.member;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int BLACKJACK = 21;
    private static final int SOFT_HAND_VALUE = 10;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void appendCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalValue() {
        int totalSum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
        if (hasAce() && totalSum + SOFT_HAND_VALUE <= BLACKJACK) {
            return totalSum + SOFT_HAND_VALUE;
        }
        return totalSum;
    }

    public boolean isBust() {
        return calculateTotalValue() > BLACKJACK;
    }

    public boolean isBlackjack() {
        return calculateTotalValue() == BLACKJACK;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
