package domain.card;

import java.util.List;

public class Cards {

    public static final int BLACKJACK_NUMBER = 21;
    private static final int ADDITIONAL_A_VALUE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int getScore() {
        int sum = getSum();
        if (hasA()) {
            return calculateAValues(sum);
        }
        return sum;
    }

    private int getSum() {
        return cards.stream()
            .map(Card::getNumber)
            .mapToInt(Denomination::getValue)
            .sum();
    }

    private boolean hasA() {
        return cards.stream()
            .anyMatch(card -> card.is(Denomination.A));
    }

    private int calculateAValues(int sum) {
        if (sum + ADDITIONAL_A_VALUE > BLACKJACK_NUMBER) {
            return sum;
        }
        return sum + ADDITIONAL_A_VALUE;
    }

    public boolean isBlackJack() {
        return getSize() == 2 && getScore() == BLACKJACK_NUMBER;
    }

    public int getSize() {
        return cards.size();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card get(int index) {
        return cards.get(index);
    }
}
