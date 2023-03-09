package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class DealerResult {

    private final Map<WinResult, Integer> dealerResult;

    public DealerResult() {
        dealerResult = new HashMap<>();
        dealerResult.put(WinResult.WIN, 0);
        dealerResult.put(WinResult.PUSH, 0);
        dealerResult.put(WinResult.LOSE, 0);
    }

    public void add(WinResult winResult) {
        dealerResult.replace(winResult, getCount(winResult) + 1);
    }

    public int getCount(WinResult winResult) {
        return dealerResult.get(winResult);
    }
}
