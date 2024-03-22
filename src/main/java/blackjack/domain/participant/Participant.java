package blackjack.domain.participant;

import blackjack.domain.card.Card;

public abstract class Participant {

    protected static final int BLACKJACK_SCORE = 21;

    private final String name;
    private final Hand hand;

    Participant(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void initializeHand(Card card1, Card card2) {
        hand.put(card1);
        hand.put(card2);
    }

    public int calculate() {
        return hand.calculate();
    }

    public void putCard(Card card) {
        hand.put(card);
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return calculate() > BLACKJACK_SCORE;
    }

    public abstract boolean canHit();
}
