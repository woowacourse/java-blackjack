package blackjack.domain.money;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(String money) {
        validate(money);
        validateMoreThanZero(parseInt(money));
    }

    private int validateMoreThanZero(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 최소 1원 이상이어야합니다.");
        }
        return money;
    }

    public Money(int money) {
        this.money = money;
    }

    private void validate(String money) {
        validateEmptyOrNull(money);
    }

    private void validateEmptyOrNull(String money) {
        if (Objects.isNull(money) || money.length() == 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 입력 가능합니다.");
        }
    }

    private int parseInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 입력 가능합니다.");
        }
    }

    public double getMoney() {
        return money;
    }
}
