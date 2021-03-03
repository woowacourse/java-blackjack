package blackjack.domain;

import blackjack.domain.card.Card;

public class Dealer extends User {
    @Override
    public void hit(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isStay() {
        int totalSum = 0;
        for (Card card : cards) {
            totalSum += card.getScore();
        }

        return totalSum > 16;
    }
}
