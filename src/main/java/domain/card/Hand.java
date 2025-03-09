package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Hand {
    private static final int ORIGINAL_ACE_VALUE = 11;
    private static final int BUST_THRESHOLD = 21;
    public static final int INITIAL_SIZE = 2;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean containsOriginalAce() {
        return cards.stream().anyMatch(originalAcePredicate());
    }

    public void setOriginalAceValueToOne() {
        Ace originalAce = (Ace) cards.stream()
                .filter(originalAcePredicate())
                .findFirst()
                .orElseThrow();
        originalAce.setValueToOne();
    }

    public void setAllCardValueToZero() {
        for (Card card : cards) {
            card.setValueToZero();
        }
    }

    public boolean isBust() {
        return getTotal() > BUST_THRESHOLD;
    }

    private Predicate<Card> originalAcePredicate() {
        return card -> card.isAce() && card.getValue() == ORIGINAL_ACE_VALUE;
    }

    public int getTotal() {
        return cards.stream().mapToInt(Card::getValue).sum();
    }

    public int getSize() {
        return cards.size();
    }

    public int getExtraSize() {
        return getSize() - INITIAL_SIZE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }
}
