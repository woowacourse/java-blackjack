package blackjack.domain;

import java.util.Collections;
import java.util.List;

public abstract class Participant {
    protected final Hand hand;
    protected final Score score;

    protected Participant(List<Card> cards) {
        this.hand = new Hand(cards);
        this.score = hand.calculateScore();
    }

    abstract boolean isAbleToReceive();

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public Hand getHand() {
        return hand;
    }

    public int getScore() {
        return score.getScore();
    }
}
