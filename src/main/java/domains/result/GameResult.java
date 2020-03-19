package domains.result;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    public static final int INITIAL_COUNT = 0;
    public static final int ADD_COUNT = 1;

    private Map<Player, ResultType> playerResult;

    public GameResult(Map<Player, ResultType> playerResult) {
        this.playerResult = playerResult;
    }

    public ResultType getPlayerGameResult(Player player) {
        return playerResult.get(player);
    }

    public Map<ResultType, Integer> calculateDealerResult() {
        Map<ResultType, Integer> dealerResult = new HashMap<>();

        for (ResultType resultType : ResultType.values()) {
            dealerResult.put(resultType, INITIAL_COUNT);
        }

        for (ResultType result : playerResult.values()) {
            dealerResult.put(result.oppose(), dealerResult.get(result) + ADD_COUNT);
        }

        return dealerResult;
    }

    public Map<Player, ResultType> getPlayerResult() {
        return playerResult;
    }
}
