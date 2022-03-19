package blackjack.domain.vo;

import java.util.Objects;

public class BettingMoney {
    private final int bettingMoney;

    public BettingMoney(int money) {
        validateBettingMoney(money);
        this.bettingMoney = money;
    }

    private void validateBettingMoney(int money) {
        validateNegative(money);
        validateBettingMoneyUnit(money);
    }

    private void validateBettingMoneyUnit(int money) {
        if ((money % 10) != 0) {
            throw new IllegalArgumentException("배팅 금액은 10원 단위여야 합니다.");
        }
    }

    private void validateNegative(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원 이하일 수 없습니다.");
        }
    }

    public int calculateRevenue(double earningRate) {
        return (int) (bettingMoney * earningRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
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
