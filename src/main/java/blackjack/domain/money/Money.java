package blackjack.domain.money;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(final String money) {
        validate(money);
        this.money = validateMoreThanZero(parseInt(money));
    }

    public Money(final int money) {
        this.money = money;
    }

    private int validateMoreThanZero(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 최소 1원 이상이어야합니다.");
        }
        return money;
    }

    private void validate(final String money) {
        validateEmptyOrNull(money);
    }

    private void validateEmptyOrNull(final String money) {
        if (Objects.isNull(money) || money.length() == 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 입력 가능합니다.");
        }
    }

    private int parseInt(final String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 입력 가능합니다.");
        }
    }

    public double getProfit(double earningRate) {
        return money * earningRate;
    }
}
