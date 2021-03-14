package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Profits;

public abstract class Running extends Started {
    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Profits profit(BettingMoney money) {
        throw new IllegalStateException();
    }

    @Override
    public boolean isBlackJack() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isStay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isBust() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isWin(State state) {
        throw new IllegalStateException();
    }
}
