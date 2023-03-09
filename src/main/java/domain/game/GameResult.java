package domain.game;

import domain.user.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    
    public enum Result {
        WIN,
        DRAW,
        LOSE
    }
    
    private final Map<Player, Result> resultCacheMap = new HashMap<>();
    
    public void accumulate(Player player, Result result) {
        this.resultCacheMap.put(player, result);
    }
    
    public HashMap<Result, Integer> generateDealerResult() {
        HashMap<Result, Integer> dealerResult = new HashMap<>();
        dealerResult.put(Result.WIN, 0);
        dealerResult.put(Result.DRAW, 0);
        dealerResult.put(Result.LOSE, 0);
        for (Result result : this.resultCacheMap.values()) {
            if (result == Result.WIN) {
                dealerResult.put(Result.LOSE, dealerResult.get(Result.LOSE) + 1);
            }
            if (result == Result.DRAW) {
                dealerResult.put(Result.DRAW, dealerResult.get(Result.DRAW) + 1);
            }
            if (result == Result.LOSE) {
                dealerResult.put(Result.WIN, dealerResult.get(Result.WIN) + 1);
            }
        }
        return dealerResult;
    }
    
    public HashMap<String, Result> getResultMap() {
        List<String> names = this.resultCacheMap.keySet().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        List<Result> results = new ArrayList<>(this.resultCacheMap.values());
        HashMap<String, Result> resultMap = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            resultMap.put(names.get(i), results.get(i));
        }
        return resultMap;
    }
}
