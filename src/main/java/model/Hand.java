package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return cards.stream().anyMatch(Card::isOriginalAce);
    }

    public void setOriginalAceValueToOne() {
        Ace originalAce = (Ace) cards.stream()
                .filter(Card::isOriginalAce)
                .findFirst()
                .orElseThrow();
        originalAce.setValueToOne();
    }

    public void setAllCardValueToZero() {
        cards.forEach(Card::setValueToZero);
    }

    public boolean isBust() {
        return getTotal() > BUST_THRESHOLD;
    }

    public int getTotal() {
        return cards.stream().mapToInt(Card::getValue).sum();
    }

    public int getSize() {
        return cards.size();
    }

    public int getExtraSize() {
        return cards.size() - INITIAL_SIZE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return new Card(cards.getFirst());
    }

    public boolean isBlackJack() {
        return getSize() == INITIAL_SIZE && getTotal() == BUST_THRESHOLD;
    }
}
