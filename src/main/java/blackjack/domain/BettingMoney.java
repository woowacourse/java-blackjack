package blackjack.domain;

import java.util.Objects;

public class BettingMoney {

    private static final String ERROR_VALIDATE_MONEY = "올바른 금액을 입력하여 주세요";
    private static final String ERROR_POSITIVE_MONEY = "금액은 0원 미만이 될 수 없습니다.";

    private int bettingMoney;

    public BettingMoney(String enterMoney) {
        int money = changeMoneyToInt(enterMoney);
        validatePositive(money);
        this.bettingMoney = money;
    }

    private void validatePositive(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(ERROR_POSITIVE_MONEY);
        }
    }

    public BettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }


    private int changeMoneyToInt(String bettingMoney) {
        try {
            return Integer.parseInt(bettingMoney);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ERROR_VALIDATE_MONEY);
        }
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    public void giveMoney(BettingMoney plusBettingMoney) {
        bettingMoney += plusBettingMoney.getBettingMoney();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BettingMoney)) {
            return false;
        }
        BettingMoney that = (BettingMoney) o;
        return bettingMoney == that.bettingMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettingMoney);
    }
}
