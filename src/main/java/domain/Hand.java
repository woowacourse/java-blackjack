package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand = new ArrayList<>();

    public Hand() {}

    public void drawCard(Card card) {
        this.hand.add(card);
    }

    public boolean isBust() {
        return calculateSum() > 21;
    }

    public int calculateSum() {
        int sum = 0;
        int aceCount = 0;

        for (Card card : hand) {
            sum += card.getScore();
            if (card.isAce()) {
                aceCount++;
            }
        }

        sum = handleAce(sum, aceCount);

        return sum;
    }

    private int handleAce(int sum, int aceCount) {
        while (sum > 21 && aceCount > 0) {
            aceCount--;
            sum -= 10;
        }
        return sum;
    }
}
