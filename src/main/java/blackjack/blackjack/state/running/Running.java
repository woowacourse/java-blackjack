package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import blackjack.blackjack.state.finished.Stay;
import blackjack.blackjack.state.started.Started;
import java.math.BigDecimal;

public abstract class Running extends Started {

    public Running(final Hand hand) {
        super(hand, StateType.RUNNING);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }

    @Override
    public boolean isNotFinished() {
        return true;
    }

    @Override
    public BigDecimal calculateProfit(final BigDecimal bettingAmount, final State dealerState) {
        throw new IllegalStateException("[ERROR] 게임이 아직 종료되지 않았습니다.");
    }
}
