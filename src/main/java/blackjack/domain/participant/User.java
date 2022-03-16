package blackjack.domain.participant;

import blackjack.domain.money.BetMoney;
import blackjack.domain.result.Result;

public class User extends Participant {

    private BetMoney betMoney;

    public User(String name) {
        super(name);
    }

    public void betting(int money) {
        betMoney = new BetMoney(money);
    }

    public int calculateProfit(int dealerSum) {
        Result result = Result.checkUserResult(holdingCards.cardSum(), dealerSum);
        if (result == Result.WIN) {
            return betMoney.getMoney();
        }
        if (result == Result.LOSE) {
            return betMoney.getMoney() * -1;
        }
        return 0;
    }
}
