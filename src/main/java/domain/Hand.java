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

    public List<Card> cards() {
        return cards;
    }

    public void appendCard(Card card) {
        validateDuplicate(card);
        cards.add(card);
    }

    public int calculateTotalValue() {
        int aceAmount = aceAmount();
        if (aceAmount > 0) {
            return softHandAces(aceAmount);
        }
        AtomicInteger tempValue = new AtomicInteger();
        cards.forEach(card -> tempValue.addAndGet(card.number()));
        return tempValue.get();
    }

    private int softHandAces(int aceAmount) {
        AtomicInteger sumWithoutAce = new AtomicInteger();
        cards.stream()
                .filter(card -> card.number() != CardNumber.ACE.getValue())
                .forEach(card -> sumWithoutAce.addAndGet(card.number()));
        if (sumWithoutAce.get() >= CardNumber.ACE.getValue()) {
            return sumWithoutAce.get() + aceAmount;
        }
        return sumWithoutAce.get() + CardNumber.ACE.getValue() + aceAmount - 1;
    }

    private int aceAmount() {
        return Math.toIntExact(
                cards.stream()
                        .filter(card -> card.number() == CardNumber.ACE.getValue())
                        .count()
        );
    }

    private void validateDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new DuplicatedException();
        }
    }
}
