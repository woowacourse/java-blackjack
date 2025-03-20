package state.running;

import card.Cards;
import state.started.Started;

public abstract class Running extends Started {

    public Running(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(final int bettingMoney) {
        throw new IllegalStateException("[ERROR] 게임 중에는 수익률 계산을 할 수 없습니다.");
    }
}
