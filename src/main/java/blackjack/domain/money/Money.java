package blackjack.domain.money;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(String money) {
        validate(money);
        this.money = parseInt(money);
    }

    public Money(int money) {
        this.money = money;
    }

    private void validate(String money) {
        if (Objects.isNull(money) || money.length() == 0) {
            throw new IllegalArgumentException("[ERROR] Money는 숫자만 입력 가능합니다.");
        }
    }

    private int parseInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] Money는 숫자만 입력 가능합니다.");
        }
    }


    public double getMoney() {
        return money;
    }
}
