package blackjack.domain.state;

import blackjack.domain.card.UserDeck;
import blackjack.domain.user.Money;

public abstract class Finished implements State {

    private static final String FINISHED_ERROR = "[ERROR] 더 이상 카드를 받을 수 없습니다.";

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(UserDeck userDeck) {
        throw new IllegalStateException(FINISHED_ERROR);
    }

    @Override
    public double profit(Money money) {
        return money.getEarning(earningRate());
    }

    public abstract double earningRate();
}
