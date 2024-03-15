package blackjack.domain.rule.state;

import blackjack.domain.card.Hands;

public final class StandState extends FinishState {

    StandState(final Hands hands) {
        super(hands);
    }

    @Override
    public State copy() {
        return new StandState(hands());
    }

    @Override
    public boolean isStand() {
        return true;
    }
}
