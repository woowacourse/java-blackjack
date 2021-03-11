package blackjack.domain.participant.state;

import blackjack.domain.Result;

public abstract class Running extends AfterInit {

    public Running(final Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Result calculatePlayerResult(final State state) {
        throw new UnsupportedOperationException();
    }
}
