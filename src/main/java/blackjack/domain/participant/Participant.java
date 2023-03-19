package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Participant {

    protected static final Score BLACKJACK_SCORE = new Score(21);

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Score calculateScore() {
        return hand.calculateScore();
    }

    public abstract boolean canReceive();

    public Hand getHand() {
        return hand;
    }
}
