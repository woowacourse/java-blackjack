package blackjack.model.participant;

public class DealerProfit {

    private static final int NEGATIVE_MULTIPLIER = -1;

    private final int amount;

    public DealerProfit(int playerTotalPrize) {
        this.amount = NEGATIVE_MULTIPLIER * playerTotalPrize;
    }

    public int getAmount() {
        return amount;
    }
}
