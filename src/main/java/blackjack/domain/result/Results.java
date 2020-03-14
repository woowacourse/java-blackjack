package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.HashMap;
import java.util.Map;

public class Results {
    private Map<Name, ResultType> playerResult;
    private Map<ResultType, Integer> dealerResult;

    public Results(Map<Name, ResultType> playerResult,
                   Map<ResultType, Integer> dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public static Results createResults(Players players, Dealer dealer) {
        Map<Name, ResultType> playerResult = new HashMap<>();
        Map<ResultType, Integer> dealerResult = new HashMap<>();
        for (ResultType resultType : ResultType.values()) {
            dealerResult.put(resultType, 0);
        }

        for (Player player : players.getPlayers()) {
            ResultType playerResultType = computePlayerResult(player, dealer);
            playerResult.put(player.getName(), playerResultType);
            dealerResult.compute(ResultType.opposite(playerResultType), (k, v) -> ++v);
        }
        return new Results(playerResult, dealerResult);
    }

    private static ResultType computePlayerResult(Player player, Dealer dealer) {
        return ResultType.computeResult(player.getPoint(), dealer.getPoint());
    }

    public Map<Name, ResultType> getPlayerResult() {
        return playerResult;
    }

    public Map<ResultType, Integer> getDealerResult() {
         return dealerResult;
    }
}
