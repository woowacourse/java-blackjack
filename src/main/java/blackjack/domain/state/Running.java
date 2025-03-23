package blackjack.domain.state;

import blackjack.domain.card.Hand;
import java.math.BigDecimal;

public abstract class Running extends Started {

    public Running(final Hand cards) {
        super(cards, StateType.RUNNING);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public boolean isNotFinished() {
        return true;
    }

    @Override
    public BigDecimal profit(final BigDecimal bettingAmount, final State dealerState) {
        throw new IllegalStateException("[ERROR] 게임이 아직 종료되지 않았습니다.");
    }
}
