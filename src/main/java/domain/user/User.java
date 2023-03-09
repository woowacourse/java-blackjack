package domain.user;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;

public abstract class User {

    protected final Hand hand = new Hand();

    public void hit(final Card card) {
        hand.addCard(card);
    }

    public Hand getCards() {
        return hand;
    }

    abstract public boolean isHittable();

    abstract public boolean isDealer();

    abstract public boolean isPlayer();

    public Score getScore() {
        return hand.getSumOfScores();
    }
}
