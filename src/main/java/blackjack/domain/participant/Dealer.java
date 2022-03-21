package blackjack.domain.participant;

import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public class Dealer extends Participant {

    private static final int DEALER_UNDER_SCORE = 16;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer() {
        this(new Ready());
    }

    private Dealer(State state) {
        this(new Name(DEFAULT_NAME), state);
    }

    private Dealer(Name name, State state) {
        super(name, state);
    }

    @Override
    public boolean shouldReceive() {
        return getCardTotalScore() <= DEALER_UNDER_SCORE && !isFinished();
    }
}
