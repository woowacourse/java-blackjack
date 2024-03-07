package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {
    private static final int BLACKJACK_BOUND = 21;

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    abstract boolean canReceiveCard();

    public boolean isBurst() {
        return hand.calculateScore() > BLACKJACK_BOUND;
    }

    public long getScore() {
        return hand.calculateScore();
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }
}
