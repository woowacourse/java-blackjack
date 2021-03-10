package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.state.State;

import java.math.BigDecimal;

public class Player extends Participant {

    public Player(Nickname nickname) {
        super(nickname);
    }

    public Player(Nickname nickname, State state) {
        super(nickname, state);
    }

    public void betting(BettingMoney bettingMoney) {
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
