package blackjack.domain.bet;

public class Bet {

    private final int amount;

    public Bet(int amount) {
        this.amount = amount;
    }

    public int calculateProfit(ProfitRate profitRate) {
        return profitRate.getProfit(amount);
    }
}
