package domain;

import java.util.List;

public class CardPool {

    private final List<Card> cards;

    public CardPool(List<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public int sumCardNumbers() {
        int sum = 0;
        for (Card card : cards) {
            sum = sumCardNumber(sum, card);
        }

        return sum;
    }

    private int sumCardNumber(int sum, Card card) {
        if (card.getNumber() == CardNumber.ACE && sum > 10) {
            return sum + 1;
        }
        return sum + card.getNumber().getValue();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
