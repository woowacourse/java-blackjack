package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.state.Ready;

public class Player extends Participant {

    private final BettingMoney bettingMoney;

    public Player(String name, int money) {
        super(new Name(name), new Ready());
        this.bettingMoney = new BettingMoney(money);
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    public void stay() {
        state = state.stay();
    }
}
