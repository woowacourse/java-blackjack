package domain;

import java.util.List;

public class Cards {
    private static final int BLACKJACK_NUMBER = 21;

    private List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isBust() {
        return getSum() > BLACKJACK_NUMBER;
    }

    private int getSum() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getNumber)
                .sum();
    }
}
