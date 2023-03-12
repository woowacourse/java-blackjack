package domain.participant;

import java.util.Objects;

public class BetAmount {
    private static final int BET_UNIT = 1_000;
    private static final int MIN_BET_RANGE = 1_000;
    private static final int MAX_BET_RANGE = 1_000_000;
    private static final int DEFAULT_BET_MONEY = 1_000_000;
    private static final String ERROR_BET_RANGE = "[ERROR] 1,000 ~ 1,000,000 사이의 금액만 배팅 가능합니다";
    private static final String ERROR_BET_UNIT = "[ERROR] 배팅 금액은 1,000원 단위만 가능합니다";

    private final int money;

    private BetAmount(int money) {
        validate(money);
        this.money = money;
    }

    public static BetAmount from(int money) {
        return new BetAmount(money);
    }

    public static BetAmount setDefaultBetting() {
        return new BetAmount(DEFAULT_BET_MONEY);
    }

    private void validate(int money) {
        validateMoneyRange(money);
        validateMoneyUnits(money);
    }

    private void validateMoneyRange(final int money) {
        if (money < MIN_BET_RANGE || money > MAX_BET_RANGE) {
            throw new IllegalArgumentException(ERROR_BET_RANGE);
        }
    }

    private void validateMoneyUnits(int money) {
        if (money % BET_UNIT != 0) {
            throw new IllegalArgumentException(ERROR_BET_UNIT);
        }
    }

    public static BetAmount calculateProfit(PlayerGameResult playerGameResult, BetAmount betAmount) {
        return BetAmount.from(playerGameResult.calculateBenefit(betAmount.getMoney()));
    }

    public int getMoney() {
        return this.money;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BetAmount betAmount = (BetAmount) o;
        return money == betAmount.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
