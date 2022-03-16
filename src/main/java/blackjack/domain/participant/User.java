package blackjack.domain.participant;

import blackjack.domain.money.Money;
import blackjack.domain.result.Result;

public class User extends Participant {

    private Money bettingMoney;

    public User(String name) {
        super(name);
    }

    public void betting(int money) {
        bettingMoney = new Money(money);
    }

    public int calculateProfit(int dealerSum) {
        Result result = Result.checkUserResult(holdingCards.cardSum(), dealerSum);
        if (result == Result.WIN) {
            return bettingMoney.getMoney();
        }
        if (result == Result.LOSE) {
            return bettingMoney.getMoney() * -1;
        }
        return 0;
    }
}
