package blackjack.domain.game;

import blackjack.domain.participant.Player;

public class BettingCashier {

    private final Betting betting;
    private final Result result;

    public BettingCashier(Betting betting, Result result) {
        this.betting = betting;
        this.result = result;
    }

    public BlackjackMoney findProfitOfDealer() {
        BlackjackMoney totalProfit = new BlackjackMoney();
        for (Player player : betting.getPlayers()) {
            totalProfit = totalProfit.add(findProfitOf(player));
        }
        return totalProfit.toNegative();
    }

    public BlackjackMoney findProfitOf(Player player) {
        BlackjackMoney money = betting.findMoneyOf(player);
        if (result.isPlayerWon(player)) {
            return applyMultipleIfBlackjack(player, money);
        }
        if (result.isPlayerLose(player)) {
            return money.toNegative();
        }
        return new BlackjackMoney();
    }

    private BlackjackMoney applyMultipleIfBlackjack(Player player, BlackjackMoney money) {
        if (player.isBlackjack()) {
            return money.applyBlackjackMultiple();
        }
        return money;
    }
}
