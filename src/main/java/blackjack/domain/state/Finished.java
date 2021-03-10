package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.domain.Money;

public abstract class Finished extends StartState {

    private static final String ERROR_DRAW = "드로우를 할 기회가 없습니다.";

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException(ERROR_DRAW);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int profit(Money money) {
        return (int) (money.getMoney() * earningRate());
    }


    public abstract double earningRate();

}
