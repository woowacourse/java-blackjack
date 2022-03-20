package blackjack.domain.result;

import blackjack.domain.player.BetMoney;

import java.util.Objects;

public class Profit {

    private static final int INIT_RATIO = 1;

    private final double money;
    private final double ratio;

    public Profit(double money) {
        this.ratio = INIT_RATIO;
        this.money = money;
    }

    public Profit(double ratio, BetMoney betMoney) {
        this.ratio = ratio;
        this.money = betMoney.getValue();
    }

    public double getValue() {
        return ratio * money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
        return Double.compare(profit.money, money) == 0 && Double.compare(profit.ratio, ratio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, ratio);
    }
}
