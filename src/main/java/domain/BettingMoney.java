package domain;

import static domain.util.NullValidator.validateNull;

public class BettingMoney {
    public static final int MIN_MONEY = 1;

    private final int money;

    public BettingMoney(String money) {
        validate(money);
        this.money = Integer.parseInt(money);
    }

    private void validate(String money) {
        validateNull(money);
        validateNumber(money);
        validateValue(money);
    }

    private void validateNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로만 구성된 입력값이어야 합니다.");
        }
    }

    private void validateValue(String money) {
        int value = Integer.parseInt(money);
        if (value < MIN_MONEY) {
            throw new IllegalArgumentException(String.format("입력값은 %d 이상이어야 합니다.", MIN_MONEY));
        }
    }

    public int get() {
        return money;
    }
}
