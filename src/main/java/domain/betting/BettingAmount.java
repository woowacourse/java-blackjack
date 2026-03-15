package domain.betting;

import java.util.Objects;

public class BettingAmount {
    private final int money;

    public BettingAmount(Integer bettingAmount) {
        validateMinus(bettingAmount);
        this.money = bettingAmount;
    }

    private static void validateMinus(Integer bettingAmount) {
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 없습니다.");
        }
    }

    public Integer getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingAmount that = (BettingAmount) o;
        return Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(money);
    }
}
