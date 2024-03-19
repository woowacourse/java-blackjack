package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;
import java.util.Map;

public class Cashier {

    private final PlayersResult playersResult;
    private final Judge judge;

    public Cashier(PlayersResult playersResult, Judge judge) {
        this.playersResult = playersResult;
        this.judge = judge;
    }

    public void calculateBetResult(List<Player> players, Dealer dealer) {
        judge.decideResult(players, dealer);
        Map<Player, WinState> playersResult = judge.getPlayersResult();
        calculatePlayersProfit(playersResult);
    }

    private void calculatePlayersProfit(Map<Player, WinState> playersResult) {
        for (Player player : playersResult.keySet()) {
            this.playersResult.applyProfitToPlayer(player, playersResult.get(player));
        }
    }

    public Money calculateDealerProfit() {
        return new Money(-playersResult.sumAllPlayerProfit());
    }

    public Map<Player, Money> getPlayersResult() {
        return playersResult.getPlayerResult();
    }
}
