package domain.player;

import domain.Score;
import domain.card.Card;
import domain.hand.Hand;

import java.util.List;

public abstract class Player {

    protected final Hand hand;
    private final Name name;

    protected Player(final Name name) {
        this.name = name;
        hand = new Hand();
    }

    public Name name() {
        return name;
    }

    public Hand hand() {
        return hand;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void hit(final Card card) {
        hand.addCard(card);
    }

    public abstract boolean canHit();

    public Score score() {
        return hand.calculate();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public abstract List<Card> faceUpFirstDeal();
}
