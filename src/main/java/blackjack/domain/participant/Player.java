package blackjack.domain.participant;

import blackjack.domain.BettingMoney;

import java.math.BigDecimal;

public class Player extends Participant {

    public Player(Nickname nickname) {
        super(nickname);
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
