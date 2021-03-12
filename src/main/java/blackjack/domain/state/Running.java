package blackjack.domain.state;

import blackjack.domain.user.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public abstract class Running extends Started {
    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final double getProfit(Dealer dealer, Money money) {
        throw new IllegalArgumentException("게임 진행 중에는 수익을 구할 수 없습니다.");
    }

    @Override
    public final double earningRate(Dealer dealer) {
        return 0;
    }
}
