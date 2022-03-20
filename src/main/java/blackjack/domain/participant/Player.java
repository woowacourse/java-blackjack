package blackjack.domain.participant;

import blackjack.domain.state.Ready;

public class Player extends Participant {

    private final BettingMoney bettingMoney;

    public Player(String name, int money) {
        super(new Name(name), new Ready());
        this.bettingMoney = new BettingMoney(money);
    }

    @Override
    public boolean isDrawable() {
        return !state.isFinished();
    }

    public void stay() {
        state = state.stay();
    }

    public int calculateProfit(Participant dealer) {
        double earningRate = state.earningRate(dealer.state);
        BettingMoney profit = bettingMoney.times(earningRate);
        return profit.getAmount();
    }
}
