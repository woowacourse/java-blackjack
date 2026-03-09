package domain;

import constant.PolicyConstant;

public class Participant {

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.calculateScore() > PolicyConstant.BLACKJACK_SCORE;
    }

    public int calculateScore() {
        return hand.calculateScore();
    }
}
