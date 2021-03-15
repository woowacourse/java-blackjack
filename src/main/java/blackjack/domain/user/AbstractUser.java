package blackjack.domain.user;

import blackjack.domain.state.State;

import java.math.BigDecimal;

public abstract class AbstractUser {
    private State state;
    private final BigDecimal bettingMoney;

    protected AbstractUser(State state, BigDecimal bettingMoney) {
        validateMoney(bettingMoney);
        this.state = state;
        this.bettingMoney = bettingMoney;
    }

    public final void changeState(State state) {
        this.state = state;
    }

    public final State getState() {
        return state;
    }

    public final BigDecimal getBettingMoney() {
        return bettingMoney;
    }

    public abstract String getName();

    public abstract boolean canDraw();

    public abstract boolean isDealer();

    public abstract boolean isPlayer();

    private void validateMoney(BigDecimal bettingMoney) {
        if (bettingMoney.intValue() < 0) {
            throw new IllegalArgumentException("양수의 금액만 가능합니다.");
        }
    }
}
