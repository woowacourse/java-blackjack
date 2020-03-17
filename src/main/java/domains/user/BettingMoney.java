package domains.user;

import java.util.Objects;

public class BettingMoney {
    public static final int MINIMUM_MONEY = 1;
    private int money;

    public BettingMoney(String input) {
        checkNullAndEmpty(input);
        checkNumberFormat(input);
        int money = Integer.parseInt(input);
        checkNumberBound(money);
        this.money = money;
    }

    private void checkNumberBound(int money) {
        if (money < MINIMUM_MONEY) {
            throw new InvalidBettingMoneyException(InvalidBettingMoneyException.ZERO_OR_NEGATIVE);
        }
    }

    private void checkNullAndEmpty(String money) {
        if (Objects.isNull(money) || money.isEmpty()) {
            throw new InvalidBettingMoneyException(InvalidBettingMoneyException.NULL_OR_EMPTY);
        }
    }

    private void checkNumberFormat(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new InvalidBettingMoneyException(InvalidBettingMoneyException.NOT_NUMBER_FORMAT);
        }
    }
}
