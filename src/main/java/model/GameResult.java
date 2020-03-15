package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    Players players;
    Dealer dealer;
    Map<Player, Result> playerResult = new LinkedHashMap<>();
    Map<Result, Integer> dealerResult = new LinkedHashMap<>();

    public GameResult(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        for(Result result : Result.values()){
            dealerResult.put(result, 0);
        }
    }

    public void calculateResults() {
        for(Player player :players){
            Result result = Result.compete(dealer, player);
            Result oppositeResult = Result.oppositeResult(result);
            int count = dealerResult.get(oppositeResult);

            playerResult.put(player, result);
            dealerResult.put(oppositeResult, count + 1);
        }
    }

    public Map<Player, Result> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }

    public Map<Result, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}
