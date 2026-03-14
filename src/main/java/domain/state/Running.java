package domain.state;

import domain.participant.Hand;
import java.math.BigDecimal;

public abstract class Running extends Started {
    protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        return new Stay(getHand());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public BigDecimal calculateProfitRate(State dealerState) {
        throw new IllegalStateException("게임 진행 중에는 수익률을 계산할 수 없습니다.");
    }
}
