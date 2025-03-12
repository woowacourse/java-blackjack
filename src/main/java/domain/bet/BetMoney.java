package domain.bet;

import java.util.Objects;

public class BetMoney {

    private final double amount;

    public BetMoney(final double amount) {
        this.amount = amount;
    }

    public BetMoney applyBlackJackBonus() {
        return new BetMoney(amount * 1.5);
    }

    public BetMoney applyWinBonus() {
        return new BetMoney(amount * 2);
    }

    public BetMoney applyLossPenalty() {
        return new BetMoney(0);
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BetMoney betMoney = (BetMoney) o;
        return Double.compare(amount, betMoney.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
