package blackjack.domain.state;

import blackjack.domain.BettingMoney;
import blackjack.domain.Cards;

public abstract class Finished extends StartState {

    private static final String ERROR_DRAW = "드로우를 할 기회가 없습니다.";
    private static final String ERROR_STAY = "이미 종료된 선택입니다.";

    @Override
    public State stay() {
        throw new UnsupportedOperationException(ERROR_STAY);
    }

    @Override
    public State draw(Cards cards) {
        throw new UnsupportedOperationException(ERROR_DRAW);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int profit(BettingMoney bettingMoney) {
        return (int) (bettingMoney.getBettingMoney() * earningRate());
    }

    public abstract double earningRate();

}
