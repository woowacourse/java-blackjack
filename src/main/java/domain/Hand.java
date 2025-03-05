package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ORIGINAL_ACE_VALUE = 11;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getTotal() {
        return cards.stream().mapToInt(Card::getValue).sum();
    }

    public boolean containsOriginalAce() {
        return cards.stream().anyMatch(card -> card.isAce() && card.getValue() == ORIGINAL_ACE_VALUE);
    }
}
