package blackjack.domain.state.running;

import blackjack.domain.cards.Hand;
import blackjack.domain.state.State;

public abstract class Running implements State {

    private static final double PROFIT = 1.0;
    private final Hand hand;

    protected Running(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double getEarningRate() {
        throw new IllegalStateException("아직 배율을 확인할 수 없습니다.");
    }

    @Override
    public Hand getHand() {
        return hand;
    }

}
