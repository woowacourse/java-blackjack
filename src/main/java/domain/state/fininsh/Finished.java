package domain.state.fininsh;

import domain.card.Card;
import domain.card.Hands;
import domain.state.Started;
import domain.state.State;

public abstract class Finished extends Started {

    public Finished(final Hands hands) {
        super(hands);
    }

    @Override
    public final State add(final Card card) {
        throw new UnsupportedOperationException("게임이 끝난 상태에서 카드를 더이상 뽑을 수 없습니다");
    }

    @Override
    public final State stand() {
        throw new UnsupportedOperationException("게임이 끝난 상태에서 스탠드를 할 수 없습니다");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    protected boolean isBust() {
        return false;
    }

    protected boolean isBlackjack() {
        return false;
    }
}
