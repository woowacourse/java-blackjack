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
        return applyMultiple(money, player);
    }

    private BlackjackMoney applyMultiple(BlackjackMoney money, Player player) {
        return money.applyMultiple(result.getPlayerState(player).getMultiple());
    }
}
