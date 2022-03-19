package blackjack.domain.participant;

import blackjack.domain.state.Ready;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME), new Ready());
    }

    @Override
    public boolean isFinished() {
        return getTotalScore() > 16;
    }
}
