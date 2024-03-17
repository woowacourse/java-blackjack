package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;

import java.util.List;

public abstract class Gamer {

    protected final Hand hand;

    public Gamer() {
        this.hand = new Hand();
    }

    public final boolean isBust() {
        return hand.isBust();
    }

    public final boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public final long getScore() {
        return hand.calculateScore();
    }

    public final List<Card> getHandCards() {
        return hand.getCards();
    }

    abstract boolean canReceiveCard();
}
