package domain.profit;

public class Profit {
    private final int profit;

    public Profit(int profit) {
        this.profit = profit;
    }

    public Profit add(int profit) {
        int newProfit = this.profit + profit;
        return new Profit(newProfit);
    }

    public int getProfit() {
        return profit;
    }
}
