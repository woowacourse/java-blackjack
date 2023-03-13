package domain.user;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;

public abstract class User {

    protected final Hand hand = new Hand();

    public final void hit(final Card card) {
        hand.addCard(card);
    }

    public abstract boolean isHittable();

    public abstract boolean isDealer();

    public abstract boolean isPlayer();

    public final Hand getHand() {
        return hand;
    }

    public final Score getScore() {
        return hand.getSumOfScores();
    }
}
