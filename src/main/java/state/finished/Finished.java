package state.finished;

import card.Card;
import card.Cards;
import state.State;
import state.started.Started;

public abstract class Finished extends Started {

    public Finished(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("[ERROR] 끝난 상태에서는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new IllegalStateException("[ERROR] 끝난 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(final int bettingMoney) {
        return earningRate() * bettingMoney;
    }

    public abstract double earningRate();
}
