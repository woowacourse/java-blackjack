package domain;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private final Map<User, Money> bets = new HashMap<>();
    private Money bankProfit = Money.of(0);

    public void bet(User user, Money money) {
        if (money.isLessThan(Money.of(100))) {
            throw new IllegalArgumentException("100원 이상 배팅해야 합니다");
        }
        Money bet = betOf(user);
        bets.put(user, bet.add(money));
    }

    public Money pay(User user, Result result) {
        Money deposit = betOf(user);
        Money pay = deposit.profit(result.getDividend());
        applyExpense(pay.sub(deposit));
        return pay;
    }

    private void applyExpense(Money money) {
        bankProfit = bankProfit.sub(money);
    }

    private Money betOf(User user) {
        return bets.getOrDefault(user, Money.of(0));
    }
}
