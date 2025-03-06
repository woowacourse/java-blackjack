package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Hand {

    private static final int ORIGINAL_ACE_VALUE = 11;
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

    public boolean isBust() {
        return getTotal() > 21;
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

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
