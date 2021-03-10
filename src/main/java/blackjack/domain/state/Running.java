package blackjack.domain.state;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public abstract class Running extends Started {
    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public BigDecimal profit(BettingMoney money) {
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
