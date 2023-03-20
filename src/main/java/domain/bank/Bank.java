package domain.bank;

import java.util.HashMap;
import java.util.Map;

import domain.Result;
import domain.participant.User;

public class Bank {

    private static final Money BET_MINIMUM = Money.of(100);

    private final Map<User, Money> principals = new HashMap<>();
    private final Map<User, Money> profits = new HashMap<>();

    public void bet(User user, Money money) {
        validateMinimum(money);
        addPrincipal(user, money);
    }

    private void validateMinimum(Money money) {
        if (money.isLessThan(BET_MINIMUM)) {
            throw new IllegalArgumentException("100원 이상 배팅해야 합니다");
        }
    }

    public void evaluate(User user, Result result) {
        Money prize = getPrincipalOf(user).multiply(result.getDividend());
        addProfit(user, prize);
    }

    private void addPrincipal(User user, Money money) {
        Money principal = getPrincipalOf(user);
        if (principal.equals(Money.ZERO)) {
            principals.put(user, money);
        }
        principals.put(user, principal.add(money));
    }

    private void addProfit(User user, Money prize) {
        Money profit = getProfitOf(user);
        if (profit.equals(Money.ZERO)) {
            profits.put(user, prize);
        }
        profits.put(user, profit.add(prize));
    }

    private Money getPrincipalOf(User user) {
        return principals.getOrDefault(user, Money.ZERO);
    }

    public Money getProfitOf(User user) {
        return profits.getOrDefault(user, Money.ZERO);
    }

    public Money getProfit() {
        Money profit = Money.ZERO;
        for (User user : principals.keySet()) {
            profit = profit.sub(getProfitOf(user));
        }
        return profit;
    }
}
