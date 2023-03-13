package domain.card;

import java.util.Iterator;
import java.util.List;

public class Cards implements Iterable<Card>{

    public static final int BLACKJACK_NUMBER = 21;
    private static final int ADDITIONAL_A_VALUE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int getScore() {
        int score = getSum();
        if (hasA()) {
            score = calculateAValues(score);
        }
        return score;
    }

    private int getSum() {
        return cards.stream()
            .mapToInt(Card::getDenominationValue)
            .sum();
    }

    private boolean hasA() {
        return cards.stream()
            .anyMatch(Card::isA);
    }

    private int calculateAValues(int score) {
        if (score + ADDITIONAL_A_VALUE > BLACKJACK_NUMBER) {
            return score;
        }
        return score + ADDITIONAL_A_VALUE;
    }

    public boolean isBlackJack() {
        return getSize() == 2 && getScore() == BLACKJACK_NUMBER;
    }

    public boolean isBurst() {
        return getScore() > BLACKJACK_NUMBER;
    }

    public int getSize() {
        return cards.size();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card getFirst() {
        return cards.get(0);
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
