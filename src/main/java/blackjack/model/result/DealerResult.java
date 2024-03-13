package blackjack.model.result;

import java.util.LinkedHashMap;
import java.util.Map;

public class DealerResult {

    private final Map<ResultState, Integer> dealerResult = new LinkedHashMap<>();

    public DealerResult() {
        for (ResultState resultState : ResultState.values()) {
            dealerResult.put(resultState, 0);
        }
    }

    public void addWin() {
        dealerResult.put(ResultState.WIN, dealerResult.get(ResultState.WIN) + 1);
    }

    public void addLose() {
        dealerResult.put(ResultState.LOSE, dealerResult.get(ResultState.LOSE) + 1);;
    }

    public void addTie() {
        dealerResult.put(ResultState.TIE, dealerResult.get(ResultState.WIN) + 1);
    }

    public Map<ResultState, Integer> getDealerResult() {
        return dealerResult;
    }
}
