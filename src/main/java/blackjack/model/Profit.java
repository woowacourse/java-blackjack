package blackjack.model;

import java.util.Objects;

public class Profit {

    public static final double BLACKJACK_WIN_PROFIT_RATE = 1.5;

    private final int amount;

    public Profit(int amount) {
        this.amount = amount;
    }

    public static boolean isDivisibleByTen(int money) {
        return money * BLACKJACK_WIN_PROFIT_RATE % 10 != 0;
    }

    public static Profit of(Betting bettingMoney, Result result, boolean isBlackjackWin) {
        return new Profit(bettingMoney.calculateResult(result, isBlackjackWin));
    }

    public int getAmount() {
        return amount;
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
