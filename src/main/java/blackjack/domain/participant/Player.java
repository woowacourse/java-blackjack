package blackjack.domain.participant;

import blackjack.domain.BettingMoney;

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
}
