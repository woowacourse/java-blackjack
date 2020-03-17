package blackjack.domain.result;

import blackjack.domain.user.*;

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

    private static ResultType computePlayerResult(User player, User dealer) {
        return ResultType.computeResult(new Point(player.getCards()), new Point(dealer.getCards()));
    }

    public Map<Name, ResultType> getPlayerResult() {
        return playerResult;
    }

    public Map<ResultType, Integer> getDealerResult() {
         return dealerResult;
    }
}
