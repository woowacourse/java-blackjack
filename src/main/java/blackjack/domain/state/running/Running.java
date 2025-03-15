package blackjack.domain.state.running;

import blackjack.domain.card.Cards;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.winning.WinningResult;

public abstract class Running extends Started {
    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public double profit(double bettingMoney) {
        throw new IllegalStateException("수익을 계산할 수 없습니다.");
    }

    @Override
    public WinningResult decide(State state) {
        throw new IllegalStateException("아직 승패를 결정할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
