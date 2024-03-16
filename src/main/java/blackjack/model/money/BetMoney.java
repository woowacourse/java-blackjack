package blackjack.model.money;

import java.util.Objects;

public class BetMoney {
    private static final int MIN_BET_MONEY = 1;
    private static final int MAX_BET_MONEY = 100_000_000;
    public static final String OUT_OF_BET_MONEY_BOUND = "1원부터 1억원까지만 베팅 가능합니다.";

    private final int betMoney;

    public BetMoney(final int betMoney) {
        validateBetMoneyInBound(betMoney);
        this.betMoney = betMoney;
    }

    private void validateBetMoneyInBound(int betMoney) {
        if (betMoney < MIN_BET_MONEY || betMoney > MAX_BET_MONEY) {
            throw new IllegalArgumentException(OUT_OF_BET_MONEY_BOUND);
        }
    }

    public int multiply(final double multiplier) {
        return (int) (betMoney * multiplier);
    }

    public int getBetMoney() {
        return betMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BetMoney betMoney1 = (BetMoney) o;
        return betMoney == betMoney1.betMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betMoney);
    }
}
