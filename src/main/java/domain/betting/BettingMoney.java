package domain.betting;

import util.Constants;

public class BettingMoney {

    private static final String LESS_OR_EQUAL_ZERO_ERROR_MESSAGE = "배팅 금액은 0보다 커야합니다.";
    private static final String NOT_NUMERIC_ERROR_MESSAGE = "배팅 금액은 숫자여야합니다.";

    private final int money;

    public BettingMoney(final int money) {
        validate(money);
        this.money = money;
    }

    public BettingMoney(final String money) {
        this(convertMoney(money));
    }

    private void validate(final int money) {
        if (isLessOrEqualZero(money)) {
            throw new IllegalArgumentException(Constants.ERROR_PREFIX + LESS_OR_EQUAL_ZERO_ERROR_MESSAGE);
        }
    }

    private boolean isLessOrEqualZero(int money) {
        return money <= 0;
    }

    private static int convertMoney(final String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Constants.ERROR_PREFIX + NOT_NUMERIC_ERROR_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }
}
