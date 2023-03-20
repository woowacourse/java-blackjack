package domain.bank;

import java.util.HashMap;
import java.util.Map;

import domain.Result;
import domain.participant.User;

public class Bank {

    private static final Money BET_MINIMUM = Money.of(100);

    private final Map<User, Money> principals = new HashMap<>();
    private final Map<User, Money> deposits = new HashMap<>();

    public void bet(User user, Money money) {
        validateMinimum(money);
        deposit(user, money);
    }

    private void validateMinimum(Money money) {
        if (money.isLessThan(BET_MINIMUM)) {
            throw new IllegalArgumentException("100원 이상 배팅해야 합니다");
        }
    }

    public void evaluate(User user, Result result) {
        Money profit = getDepositOf(user).multiply(result.getDividend());
        deposit(user, profit);
    }

    public Money withdrawDepositOf(User user) {
        Money deposit = getDepositOf(user);
        clearDepositOf(user);
        return deposit;
    }

    private void deposit(User user, Money money) {
        Money deposit = getDepositOf(user);
        if (deposit.equals(Money.ZERO)) {
            principals.putIfAbsent(user, money);
        }
        deposits.put(user, deposit.add(money));
    }

    private void clearDepositOf(User user) {
        deposits.put(user, Money.ZERO);
    }

    private Money getDepositOf(User user) {
        return deposits.getOrDefault(user, Money.ZERO);
    }

    private Money getPrincipalOf(User user) {
        return principals.getOrDefault(user, Money.ZERO);
    }

    public Money getProfit() {
        Money profit = Money.ZERO;
        for (User user : deposits.keySet()) {
            profit = profit.sub(getProfitOf(user));
        }
        return profit;
    }

    public Money getProfitOf(User user) {
        return getDepositOf(user).sub(getPrincipalOf(user));
    }
}
