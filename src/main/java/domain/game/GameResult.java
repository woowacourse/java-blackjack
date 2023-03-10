package domain.game;

import domain.money.Profit;
import domain.user.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    
    private final Map<Player, ResultStatus> resultCacheMap = new HashMap<>();
    
    public void accumulate(Player player, ResultStatus resultStatus) {
        this.resultCacheMap.put(player, resultStatus);
    }
    
    public Map<String, Profit> generatePlayerProfitResult() {
        Map<String, Profit> playerProfitResult = new HashMap<>();
        
        return playerProfitResult;
    }
    
    public Map<ResultStatus, Integer> generateDealerResult() {
        HashMap<ResultStatus, Integer> dealerResult = new HashMap<>();
        dealerResult.put(ResultStatus.WIN, 0);
        dealerResult.put(ResultStatus.DRAW, 0);
        dealerResult.put(ResultStatus.LOSE, 0);
        for (ResultStatus resultStatus : this.resultCacheMap.values()) {
            if (resultStatus == ResultStatus.WIN || resultStatus == ResultStatus.WIN_BLACKJACK) {
                dealerResult.put(ResultStatus.LOSE, dealerResult.get(ResultStatus.LOSE) + 1);
            }
            if (resultStatus == ResultStatus.DRAW) {
                dealerResult.put(ResultStatus.DRAW, dealerResult.get(ResultStatus.DRAW) + 1);
            }
            if (resultStatus == ResultStatus.LOSE) {
                dealerResult.put(ResultStatus.WIN, dealerResult.get(ResultStatus.WIN) + 1);
            }
        }
        return dealerResult;
    }
    
    public Map<Player, ResultStatus> getResultMap() {
        return Collections.unmodifiableMap(this.resultCacheMap);
    }
}
