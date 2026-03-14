package model.result;

public class DealerProfit {
    private Integer profit = 0;

    public void increase(Integer betAmount) {
        profit += betAmount;
    }

    public Integer getTotalProfit() {
        return profit;
    }
}
