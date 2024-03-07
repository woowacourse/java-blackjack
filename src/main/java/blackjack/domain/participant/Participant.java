package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {
    private static final int BLACKJACK_BOUND = 21;

    protected final String name;
    protected final Hand hand;

    public Participant(final String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }
}
