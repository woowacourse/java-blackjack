package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Gamer {

    protected final Hand hand;
    protected final Betting betting;

    public Gamer(final Betting betting) {
        this.hand = new Hand();
        this.betting = betting;
    }

    abstract boolean canReceiveCard();

    public boolean isBust() {
        return hand.calculateScore() > Hand.BLACKJACK_BOUND;
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public long getScore() {
        return hand.calculateScore();
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }
}
