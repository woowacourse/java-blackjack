package domain.participant;

import domain.game.GamePoint;
import domain.game.Hand;

import java.util.Collections;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;
    protected static final String DEALER_NAME = "딜러";

    private final Name name;
    protected Hand hand;

    protected Participant(final Name name) {
        this.name = name;
        this.hand = Hand.create(Collections.emptyList());
    }

    protected Participant(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public GamePoint calculatePoint() {
        return hand.getGamePoint();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public Name getName() {
        return name;
    }

    public Hand getCards() {
        return hand;
    }
}
