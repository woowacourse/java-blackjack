package blackjack.state.started.running;

import blackjack.card.Hand;
import blackjack.state.started.Started;

public abstract class Running extends Started {

    public Running(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfit(int bettingAmount) {
        throw new IllegalStateException("[ERROR] 아직 게임이 끝나지 않아 수익을 구할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
