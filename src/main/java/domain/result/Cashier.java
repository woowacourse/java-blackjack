package domain.result;

import domain.player.Dealer;
import domain.player.Player;

import java.util.List;
import java.util.Map;

public class Cashier {

    private final PlayerMoney playerMoney;
    private final Judge judge;

    public Cashier(PlayerMoney playerMoney, Judge judge) {
        this.playerMoney = playerMoney;
        this.judge = judge;
    }

    public void calculateBetResult(List<Player> players, Dealer dealer) {
        judge.decideResult(players, dealer);
        Map<Player, WinState> playersResult = judge.getPlayersResult();
        calculatePlayersProfit(playersResult);
    }

    private void calculatePlayersProfit(Map<Player, WinState> playersResult) {
        for (Player player : playersResult.keySet()) {
            this.playerMoney.applyProfitToPlayer(player, playersResult.get(player));
        }
    }

    public Money calculateDealerProfit() {
        return new Money(-playerMoney.sumAllPlayerMoney());
    }

    public Map<Player, Money> getPlayersResult() {
        return playerMoney.getPlayerMoney();
    }
}
