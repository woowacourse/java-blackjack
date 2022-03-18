package blackJack.domain.Result;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;

import java.util.HashMap;
import java.util.Map;

public class PlayerScore {

    private Map<Player, Result> results;
    private Map<String, Integer> profits;

    public PlayerScore() {
        this.results = new HashMap<>();
        this.profits = new HashMap<>();
    }

    public void makePlayerResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            results.put(player, Result.judge(dealer, player));
        }
        makePlayersProfit();
    }

    public Map<String, Integer> makePlayersProfit() {
        for (Map.Entry<Player, Result> entry : results.entrySet()) {
            profits.put(entry.getKey().getName(), entry.getValue().calculateBenefit(entry.getKey()));
        }
        return profits;
    }

    public Map<String, Integer> getPlayersProfit() {
        return profits;
    }

    public void makeBlackjackResult(Players players) {
        for (Player player : players.getPlayers()) {
            results.put(player, player.judgeByBlackjack());
        }
        makePlayersProfit();
    }

}
