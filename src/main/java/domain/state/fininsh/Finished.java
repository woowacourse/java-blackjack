package domain.state.fininsh;

import domain.card.Card;
import domain.state.Started;
import domain.state.State;

public abstract class Finished extends Started {

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("게임이 끝난 상태에서 카드를 더이상 뽑을 수 없습니다");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("게임이 끝난 상태에서 스탠드를 할 수 없습니다");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit() {
        return 0;
    }

    public abstract double earningRate();
}
