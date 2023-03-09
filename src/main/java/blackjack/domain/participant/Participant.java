package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Participant {

    protected static final int BLACK_JACK_SCORE = 21;

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Score calculateScore() {
        Score score = hand.sum();
        int aceCount = hand.getAceCount();
        return score.calculateBestScoreAce(aceCount);
    }

    public abstract boolean canReceive();

    public Hand getHand() {
        return hand;
    }
}
