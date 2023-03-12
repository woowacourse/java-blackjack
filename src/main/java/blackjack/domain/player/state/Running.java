package blackjack.domain.player.state;

import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Hand;
import blackjack.domain.result.Score;

public abstract class Running extends PlayerStatus {
    Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public PlayerStatus stay() {
        return new Stay(hand());
    }

    @Override
    public double calculateProfit(boolean isDealerBlackJack, Score dealerScore, BettingMoney bettingMoney) {
        throw new IllegalStateException("게임이 진행 중 입니다.");
    }
}
