package blackjack.domain.participant;

import blackjack.domain.money.BetMoney;

public class User extends Participant {

    private BetMoney betMoney;

    public User(String name) {
        super(name);
    }

    public void betting(int money) {
        betMoney = new BetMoney(money);
    }

    public double calculateProfit(int dealerScore) {
        return super.state.profit(betMoney, dealerScore);
    }
}
