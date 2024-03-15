package blackjack.domain.rule.state;

import blackjack.domain.card.Hands;

public final class BlackjackState extends FinishState {

    BlackjackState(final Hands hands) {
        super(hands);
    }

    @Override
    public State copy() {
        return new BlackjackState(hands());
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
