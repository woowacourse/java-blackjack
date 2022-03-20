package blackjack.domain.participant;

import java.util.Objects;

public class Bet {

    private final double amount;

    public Bet(int amount) {
        this.amount = amount;
        validateNegative(this.amount);
    }

    private void validateNegative(final double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액 1원 이상이어야 합니다.");
        }
    }

    public double calculateProfit(final double profitRate) {
        return amount * profitRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return Double.compare(bet.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Bet{" +
                "amount=" + amount +
                '}';
    }
}
