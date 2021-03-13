package blackjack.domain.state;

import blackjack.domain.BettingMoney;

public abstract class StartState implements State {

    public abstract int profit(BettingMoney bettingMoney);

    @Override
    public boolean isBust(State state) {
        return state instanceof Bust;
    }

    @Override
    public boolean isBlackjack(State state) {
        return state instanceof Blackjack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StartState)) {
            return false;
        }
        return this.getClass() == o.getClass();
    }
}
