package blackjack.model.participants;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
import blackjack.model.blackjackgame.Profit;
import java.util.Objects;

public final class Betting {
    private static final int MINIMUM_AMOUNT = 0;

    private final int amount;

    public Betting(int amount) {
        validateMoney(amount);
        this.amount = amount;
    }

    private void validateMoney(int amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("금액은 " + MINIMUM_AMOUNT + "보다 작을 수 없다.");
        }
    }

    public Profit getProfit(PlayerProfitCalculator playerProfitCalculator) {
        return new Profit(playerProfitCalculator.calculate(amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Betting betting = (Betting) o;
        return amount == betting.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
