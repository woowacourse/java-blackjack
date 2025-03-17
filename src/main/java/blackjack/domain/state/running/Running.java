package blackjack.domain.state.running;

import blackjack.domain.card.Cards;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Stay;

public abstract class Running extends Started {
    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public double profit(Cards dealerCards, double bettingMoney) {
        throw new IllegalStateException("수익을 계산할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
