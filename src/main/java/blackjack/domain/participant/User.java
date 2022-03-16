package blackjack.domain.participant;

import blackjack.domain.money.Money;
import blackjack.domain.result.Result;
import blackjack.domain.result.UserResult;

public class User extends Participant {

    private Money bettingMoney;

    public User(String name) {
        super(name);
    }

    public void betting(int money) {
        bettingMoney = new Money(money);
    }

    public UserResult getUserInfoWithResult(int dealerSum) {
        return new UserResult(name, checkResult(dealerSum));
    }

    private Result checkResult(int dealerSum) {
        return Result.checkUserResult(holdingCards.cardSum(), dealerSum);
    }
}
