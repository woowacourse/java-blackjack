package domain;

import java.util.Objects;

public class BettingMoney implements Money {
    public static final int MIN = 1000;
    public static final String INVALID_BETTING_MONEY_MESSAGE = "베팅금액은 %d원 부터 가능합니다.";

    private final int bettingMoney;

    public BettingMoney(int bettingMoney) {
        validate(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validate(int bettingMoney) {
        if (bettingMoney < MIN) {
            throw new IllegalArgumentException(
                    String.format(INVALID_BETTING_MONEY_MESSAGE, MIN));
        }
    }

    @Override
    public Money negative() {
        return new ResultMoney(-bettingMoney);
    }

    @Override
    public Money multiply(double number) {
        return new ResultMoney(bettingMoney).multiply(number);
    }

    @Override
    public Money stay() {
        return new ResultMoney(bettingMoney);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingMoney that = (BettingMoney) o;
        return bettingMoney == that.bettingMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettingMoney);
    }

    @Override
    public int getMoney() {
        return bettingMoney;
    }

}


