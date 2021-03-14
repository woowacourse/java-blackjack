package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.state.State;

import java.math.BigDecimal;
import java.util.Objects;

public class Player extends Participant {
    private static final String NOT_ACCEPT_ADDITIONAL_BETTING = "이미 베팅하셨습니다.";

    public Player(Nickname nickname) {
        super(nickname);
    }

    public Player(Nickname nickname, State state) {
        super(nickname, state);
    }

    public void betting(BettingMoney bettingMoney) {
        if (!Objects.isNull(this.bettingMoney)) {
            throw new IllegalArgumentException(NOT_ACCEPT_ADDITIONAL_BETTING);
        }
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished();
    }

    public BigDecimal profit() {
        return state.profit(this.bettingMoney);
    }
}
