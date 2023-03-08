package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultMap {
    private final Map<Player, ResultType> results;

    private ResultMap(Map<Player, ResultType> results) {
        this.results = results;
    }

    public static ResultMap from(Players players) {
        Map<Player, ResultType> results = new LinkedHashMap<>();
        Dealer dealer = (Dealer) players.getDealer();
        List<Player> challengers = players.getChallengers();
        for (Player challenger : challengers) {
            ResultType resultType = dealer.judge(challenger);
            results.put(challenger, resultType);
        }
        return new ResultMap(results);
    }

    public ResultType getChallengerResult(Player player) {
        return results.get(player);
    }

    public Map<ResultType, Integer> getDealerResult() {
        Map<ResultType, Integer> dealerResult = new HashMap<>();
        for (ResultType resultType : results.values()) {
            ResultType dealerRank = resultType.getOpposite();
            dealerResult.put(dealerRank, dealerResult.getOrDefault(dealerRank, 0) + 1);
        }
        return dealerResult;
    }
}
