package domain;

public class Profit {

    private final long profit;

    public Profit(final long profit) {
        validateMultiplesOfTen(profit);
        this.profit = profit;
    }

    public long getProfit() {
        return profit;
    }

    private static void validateMultiplesOfTen(final long profit) {
        if (profit % 10 != 0) {
            throw new IllegalStateException("[ERROR] 금액은 10의 배수만 가능합니다.");
        }
    }
}
