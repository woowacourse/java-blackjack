package blackjack.domain.game;

import blackjack.domain.participant.Player;

public class BettingCashier {

    private final Betting betting;
    private final Result result;

    public BettingCashier(Betting betting, Result result) {
        this.betting = betting;
        this.result = result;
    }

    public int findProfitOfDealer() {
        int totalProfit = 0;
        for (Player player : betting.getPlayers()) {
            totalProfit += findProfitOf(player);
        }
        return -totalProfit;
    }

    public int findProfitOf(Player player) {
        int money = betting.findMoneyOf(player);
        if (result.isPlayerWon(player)) {
            return money;
        }
        if (result.isPlayerLose(player)) {
            return -money;
        }
        return 0;
    }
}
