package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards initialize() {
        return new Cards(new ArrayList<>());
    }

    public void add(List<Card> drawnCards) {
        cards.addAll(drawnCards);
    }

    public int calculateSum() {
        int sumWithoutAce = calculatePointSumWithoutAce();
        int aceCount = calculateAceCount();
        return calculateTotalPoint(sumWithoutAce, aceCount);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private int calculatePointSumWithoutAce() {
        return cards.stream()
                .filter(card -> !card.getValue().equals(CardValue.ACE))
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getValue().equals(CardValue.ACE))
                .count();
    }

    private int calculateTotalPoint(int sumWithoutAce, int aceCount) {
        int total = sumWithoutAce;
        for (int i = 0; i < aceCount; i++) {
            if (GameRule.isBurst(total + GameRule.SOFT_ACE.getValue())) {
                total += CardValue.ACE.getPoint();
                continue;
            }
            total += GameRule.SOFT_ACE.getValue();
        }
        return total;
    }
}
