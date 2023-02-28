package domain;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }
        return sum;
    }
}
