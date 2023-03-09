package domain.game;

import domain.user.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    
    private final Map<Player, ResultStatus> resultCacheMap = new HashMap<>();
    
    public void accumulate(Player player, ResultStatus resultStatus) {
        this.resultCacheMap.put(player, resultStatus);
    }
    
    public HashMap<ResultStatus, Integer> generateDealerResult() {
        HashMap<ResultStatus, Integer> dealerResult = new HashMap<>();
        dealerResult.put(ResultStatus.WIN, 0);
        dealerResult.put(ResultStatus.DRAW, 0);
        dealerResult.put(ResultStatus.LOSE, 0);
        for (ResultStatus resultStatus : this.resultCacheMap.values()) {
            if (resultStatus == ResultStatus.WIN) {
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
    
    public HashMap<String, ResultStatus> getResultMap() {
        List<String> names = this.resultCacheMap.keySet().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        List<ResultStatus> resultStatuses = new ArrayList<>(this.resultCacheMap.values());
        HashMap<String, ResultStatus> resultMap = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            resultMap.put(names.get(i), resultStatuses.get(i));
        }
        return resultMap;
    }
}
