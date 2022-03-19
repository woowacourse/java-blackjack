package blackjack.model;

import java.util.Objects;

public class Profit {

    private final int amount;

    public Profit(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static boolean isDivisibleByTen(int money) {
        return money * 1.5 % 10 != 0;
    }

    public static Profit of(Betting bettingMoney, Result result, boolean isBlackjackWin) {
        return new Profit(bettingMoney.calculateResult(result, isBlackjackWin));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profit)) {
            return false;
        }
        Profit profit = (Profit) o;
        return amount == profit.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
