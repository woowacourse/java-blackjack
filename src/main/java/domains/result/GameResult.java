package domains.result;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<Player, ResultType> playerResult = new HashMap<>();

    public GameResult(Players players, Dealer dealer) {
        create(players, dealer);
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
}
