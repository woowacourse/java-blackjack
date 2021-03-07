package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public abstract boolean isHitable();

    public void addCard(final Card card) {
        this.hand.addCard(card);
    }

    public boolean isBurst() {
        return this.hand.isBurst(getScore());
    }

    public int getScore() {
        return this.hand.totalScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.hand.toList());
    }
}
