package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public abstract class FinishState extends PlayingState {

    FinishState(final Hands hands) {
        super(hands);
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
