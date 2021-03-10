package blackjack.domain.state;

import blackjack.domain.BettingMoney;
import blackjack.domain.Cards;

public abstract class Running extends StartState {

    private static final String ERROR_PROFIT = "진행 중인 상태에서는 수익을 확인할 수 없습니다.";

    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int profit(BettingMoney bettingMoney) {
        throw new UnsupportedOperationException(ERROR_PROFIT);
    }
}
