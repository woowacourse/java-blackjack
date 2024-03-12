package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.TrumpCard;

import java.util.List;

public abstract class Gamer {

    protected final Hand hand;

    public Gamer() {
        this.hand = new Hand();
    }

    abstract boolean canReceiveCard();

    final public boolean isBust() {
        return hand.isBust();
    }

    final public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    final public long getScore() {
        return hand.calculateScore();
    }

    final public List<TrumpCard> getHandCards() {
        return hand.getCards();
    }
}
