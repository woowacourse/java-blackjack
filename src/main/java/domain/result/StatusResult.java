package domain.result;

import domain.user.Playable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatusResult {
    
    private final Map<Playable, ResultStatus> resultMap = new HashMap<>();
    
    public void accumulate(Playable player, ResultStatus resultStatus) {
        this.resultMap.put(player, resultStatus);
    }
    
    public Map<ResultStatus, Integer> getDealerResult() {
        HashMap<ResultStatus, Integer> dealerResult = new HashMap<>();
        dealerResult.put(ResultStatus.WIN, 0);
        dealerResult.put(ResultStatus.DRAW, 0);
        dealerResult.put(ResultStatus.LOSE, 0);
        for (ResultStatus resultStatus : this.resultMap.values()) {
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
    
    public ResultStatus getPlayerResult(Playable player) {
        return this.resultMap.get(player);
    }
    
    public Map<Playable, ResultStatus> getResultMap() {
        return Collections.unmodifiableMap(this.resultMap);
    }
}
