package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public abstract boolean isOverLimitScore();

    public void receiveCard(final Card card) {
        this.hand.addCard(card);
    }

    public Score getTotalScore() {
        return hand.totalScore();
    }

    public List<Card> toHandList() {
        return Collections.unmodifiableList(hand.toList());
    }
}
