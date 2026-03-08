package domain.member;

import domain.card.Card;
import domain.card.CardNumber;
import domain.exception.DuplicatedException;
import java.util.ArrayList;
import java.util.List;

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
        return cards.stream()
                .mapToInt(Card::number)
                .sum();
    }

    private int softHandAces(int aceAmount) {
        int sumWithoutAces = cards.stream()
                .filter(c -> c.number() != CardNumber.ACE.getValue())
                .mapToInt(Card::number)
                .sum();

        if (sumWithoutAces >= CardNumber.ACE.getValue()) {
            return sumWithoutAces + aceAmount;
        }
        return sumWithoutAces + CardNumber.ACE.getValue() + aceAmount - 1;
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
