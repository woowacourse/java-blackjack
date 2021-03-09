package blackjack.domain.betting;

import java.util.Objects;

public class BettingMoney {
    private final long money;

    public BettingMoney(long money) {
        if (isNegative(money)) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
        this.money = money;
    }

    private boolean isNegative(long money) {
        return money < 0;
    }

    public long getMoney() {
        return money;
    }

    public BettingMoney multiply(double ratio) {
        return new BettingMoney((long)(money * ratio));
    }

    public long subtract(BettingMoney that) {
        return this.money - that.money;
    }

    public static long toNegative(long bettingMoney){
        return -1 * bettingMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BettingMoney)) return false;
        BettingMoney that = (BettingMoney) o;
        return getMoney() == that.getMoney();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoney());
    }
}
