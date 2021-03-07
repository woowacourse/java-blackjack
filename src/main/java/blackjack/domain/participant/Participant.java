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

    public void addCard(final Card card) {
        this.hand.addCard(card);
    }

    public int getScore() {
        return hand.totalScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand.toList());
    }
}
