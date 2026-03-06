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
                .filter(c -> c.number() != 11)
                .forEach(c -> sumWithoutAce.addAndGet(c.number()));
        if (sumWithoutAce.get() > 10) {
            return sumWithoutAce.get() + aceAmount;
        }
        return sumWithoutAce.get() + 11 + aceAmount - 1;
    }
    /**
     * ace가 있는 경우
     * - 다음 ace는 무조건 1 / 11,1
     * - ace 제외 합이 11이상이면 ace 전부 1 /1,1
     * ace가 없는 경우
     * - 나머지 합이 11이상이면 ace 1
     * - 나머지 합이 10이하이면 ace 11 11+@
     * */

    private int aceAmount() {
        return Math.toIntExact(
                cards.stream()
                        .filter(card -> card.number() == 11)
                        .count()
        );
    }

    private void validateDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new DuplicatedException();
        }
    }
}
