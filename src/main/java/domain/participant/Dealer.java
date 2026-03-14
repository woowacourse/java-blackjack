package domain.participant;

import domain.card.Card;
import domain.state.Hit;

public class Dealer extends Participant {
    private static final int MAX_RECEIVABLE_SCORE = 16;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer() {
        super(new Name(DEFAULT_NAME), new Hit(new Hand()));
    }

    public Card getInitialCard() {
        return getFirstCard();
    }

    @Override
    public boolean canReceive() {
        return !isFinished() && getScore().value() <= MAX_RECEIVABLE_SCORE;
    }
}
