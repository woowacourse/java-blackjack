package domain.manager;

public class DealerWallet {

    private Profit profit;

    public DealerWallet() {
        this.profit = new Profit(0);
    }

    public void calculateProfit(PlayersWallet playersWallet) {
        double profitOfAllPlayers = playersWallet.sumAllProfits();
        profit = new Profit(-profitOfAllPlayers);
    }

    public Profit getProfit() {
        return profit;
    }
}
