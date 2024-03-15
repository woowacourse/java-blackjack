package domain;

public class Profit {

    private final int profit;

    public Profit(final int profit) {
        validateMultiplesOfTen(profit);
        this.profit = profit;
    }

    public int getProfit() {
        return profit;
    }

    private static void validateMultiplesOfTen(final int profit) {
        if (profit % 10 != 0) {
            throw new UnsupportedOperationException("[ERROR] 금액은 10의 배수만 가능합니다.");
        }
    }
}
