package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.state.State;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractUser {
    private final BigDecimal bettingMoney;
    private State state;

    protected AbstractUser(State state, BigDecimal bettingMoney) {
        validateMoney(bettingMoney);
        this.state = state;
        this.bettingMoney = bettingMoney;
    }

    public final State draw(Card card) {
        return state.draw(card);
    }

    public final State stay() {
        return state.stay();
    }

    public final State getState() {
        return state;
    }

    public final List<Card> getCards() {
        return state.cards().getCards();
    }

    public final BigDecimal getBettingMoney() {
        return bettingMoney;
    }

    public final BigDecimal profit(State state, BigDecimal bettingMoney) {
        return state.profit(state, bettingMoney);
    }

    public final int calculateScore() {
        return state.calculateScore();
    }

    public final boolean isFinish() {
        return state.isFinish();
    }

    public final void changeState(State state) {
        this.state = state;
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
