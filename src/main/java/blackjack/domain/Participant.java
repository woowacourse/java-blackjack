package blackjack.domain;

import java.util.List;

public class Participant {

    private final Hand hand = new Hand();

    public void take(Card card) {
        hand.add(card);
    }

    public int computeSumOfCards() {
        return hand.getSum();
    }

    public boolean canDraw() {
        return !hand.isBlackJack() && !hand.isBust();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getSum() {
        return hand.getSum();
    }
}
