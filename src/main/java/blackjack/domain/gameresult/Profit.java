package blackjack.domain.gameresult;

import java.util.Objects;

// TODO 배팅 값에 따른 손실 최대값 신경써주기
// TODO 의존 값 주입으로 batting 최대값과 일원화 생각하기
public class Profit {
    private static final int MIN_PROFIT = -10_000_000;
    private static final int MAX_PROFIT = 10_000_000;

    private final double Profit;

    private Profit(double bat) {
        this.Profit = bat;
    }

    public static Profit from(double profit) {
        validateProfit(profit);
        return new Profit(profit);
    }

    private static void validateProfit(double profit) {
        if (profit < MIN_PROFIT || profit > MAX_PROFIT) {
            throw new IllegalArgumentException("수익은 최소 " + MIN_PROFIT + "부터 "
                    + MAX_PROFIT + "까지 가능합니다.");
        }
    }

    public Double getProfit() {
        return Profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        blackjack.domain.gameresult.Profit that = (blackjack.domain.gameresult.Profit) o;
        return Objects.equals(Profit, that.Profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Profit);
    }
}
