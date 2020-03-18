package domains.result;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<Player, ResultType> playerResult;
    private Profits profits;

    public GameResult(Players players, Dealer dealer) {
        this.playerResult = new HashMap<>();
        create(players, dealer);
        this.profits = new Profits(playerResult);
    }

    private void create(Players players, Dealer dealer) {
        for (Player player : players) {
            playerResult.put(player, player.checkResultType(dealer));
        }
    }

    public ResultType getWinOrLose(Player player) {
        return playerResult.get(player);
    }

    public Map<ResultType, Integer> calculateDealerResult() {
        Map<ResultType, Integer> dealerResult = new HashMap<>();

        for (ResultType resultType : ResultType.values()) {
            dealerResult.put(resultType, 0);
        }

        for (ResultType result : playerResult.values()) {
            dealerResult.put(result.oppose(), dealerResult.get(result) + 1);
        }

        return dealerResult;
    }

    public Map<Player, ResultType> getPlayerResult() {
        return playerResult;
    }

    public Profits getProfits() {
        return profits;
    }
}
