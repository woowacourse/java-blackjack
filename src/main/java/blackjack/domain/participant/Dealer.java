package blackjack.domain.participant;

import blackjack.domain.state.Ready;

public class Dealer extends Participant {

    public Dealer() {
        super(new Name("딜러"), new Ready());
    }

    @Override
    public boolean isFinished() {
        return getTotalScore() > 16;
    }
}
