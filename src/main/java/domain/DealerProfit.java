package domain;

public class DealerProfit {
    private final int dealerProfit;

    public DealerProfit(Game game) {
        this.dealerProfit = game.calculateDealerProfit();
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
