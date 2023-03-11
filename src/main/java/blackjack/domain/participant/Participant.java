package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Participant {

    protected static final int BLACK_JACK_SCORE = 21;
    protected static final int ACE_ALTER_VALUE = 10;

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int calculateCurrentScore() {
        int sum = hand.sum();
        int aceCount = hand.getAceCount();

        while (sum > BLACK_JACK_SCORE && aceCount > 0) {
            sum -= ACE_ALTER_VALUE;
            aceCount -= 1;
        }
        return sum;
    }

    public int calculateFinalScore() {
        int finalScore = calculateCurrentScore();

        if (finalScore > BLACK_JACK_SCORE) {
            finalScore = 0;
        }
        return finalScore;
    }

    public abstract boolean canReceive();

    public Hand getCards() {
        return hand;
    }
}
