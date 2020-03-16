package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    public static final int DEFAULT_RESULT = 0;
    
    private Players players;
    private Dealer dealer;
    private Map<Player, Result> playerResult = new LinkedHashMap<>();
    private Map<Result, Integer> dealerResult = new LinkedHashMap<>();

    public GameResult(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        for(Result result : Result.values()){
            dealerResult.put(result, DEFAULT_RESULT);
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
