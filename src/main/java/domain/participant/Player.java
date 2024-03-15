package domain.participant;

import domain.name.Name;
import domain.result.ResultProfitRatio;
import vo.BettingMoney;
import vo.Profit;

public class Player extends Participant {
    private final Profit profit;

    public Player(final Name name, final BettingMoney bettingMoney) {
        super(name);
        this.profit = new Profit(bettingMoney);
    }

    public void revenue(ResultProfitRatio ratio){
        profit.apply(ratio);
    }

    @Override
    public boolean isBust() {
        if (super.isBust()) {
            profit.apply(ResultProfitRatio.LOSE);
            return true;
        }
        return false;
    }

    @Override
    public boolean canReceiveMoreCard() {
        return !isBust() && !isBlackjack();
    }
}
