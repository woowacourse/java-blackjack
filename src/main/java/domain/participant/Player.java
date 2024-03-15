package domain.participant;

import domain.name.Name;
import domain.result.ResultProfitRatio;
import vo.BettingMoney;
import vo.Account;
import vo.Profit;

public class Player extends Participant {
    private final Account account;

    public Player(Name name, Account account) {
        super(name);
        this.account = account;
    }

    public static Player register(final Name name, final BettingMoney bettingMoney){
        return new Player(name, new Account(bettingMoney));
    }

    public void revenue(ResultProfitRatio ratio){
        account.applyProfit(ratio);
    }

    public Profit profit() {
        return new Profit(account.getProfit());
    }

    @Override
    public boolean isBust() {
        if (super.isBust()) {
            account.applyProfit(ResultProfitRatio.LOSE);
            return true;
        }
        return false;
    }

    @Override
    public boolean canReceiveMoreCard() {
        return !isBust() && !isBlackjack();
    }
}
