package domain;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static final Money BET_MINIMUM = Money.of(100);

    private final Map<User, Money> deposits = new HashMap<>();
    private Money bankProfit = Money.ZERO;

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
        spend(profit);
        deposit(user, profit);
    }

    public Money withdrawDepositOf(User user) {
        Money deposit = getDepositOf(user);
        clearDepositOf(user);
        return deposit;
    }

    private void spend(Money money) {
        bankProfit = bankProfit.sub(money);
    }

    private void deposit(User user, Money money) {
        Money deposit = getDepositOf(user);
        if (deposit.equals(Money.ZERO)) {
            deposits.put(user, money);
            return;
        }
        deposits.put(user, deposit.add(money));
    }

    private void clearDepositOf(User user) {
        deposits.put(user, Money.ZERO);
    }

    private Money getDepositOf(User user) {
        return deposits.getOrDefault(user, Money.ZERO);
    }

    public Money getProfit() {
        return bankProfit;
    }
}
