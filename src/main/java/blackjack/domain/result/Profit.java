package blackjack.domain.result;

import blackjack.domain.participant.Player;

import java.util.Map;
import java.util.Objects;

public class Profit {

    private final double amount;

    private Profit(final double amount) {
        this.amount = amount;
    }

    public static Profit calculatePlayerProfit(final Player player, final PlayerResult result) {
        final double amount = player.getBettingAmount() * result.getProfitRate();

        return new Profit(amount);
    }

    public static Profit calculateDealerProfit(final Map<Player, Profit> result) {
        double total = 0;

        for (Profit profit : result.values()) {
            total += profit.negateAmount();
        }

        return new Profit(total);
    }

    private double negateAmount() {
        return -amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Profit profit = (Profit) o;
        return Double.compare(profit.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Profit{" +
                "amount=" + amount +
                '}';
    }
}
