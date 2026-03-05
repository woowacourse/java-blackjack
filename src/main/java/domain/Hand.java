package domain;

import domain.exception.DuplicatedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void appendCard(Card card) {
        validateDuplicate(card);
        cards.add(card);
    }

    public int calculateTotalValue() {
        AtomicInteger tempValue = new AtomicInteger();
        cards.forEach(card -> tempValue.addAndGet(card.number()));
        return tempValue.get();
    }

    private void validateDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new DuplicatedException();
        }
    }
}
