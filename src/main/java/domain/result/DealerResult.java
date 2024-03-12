package domain.result;

import domain.WinState;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealerResult {

    private final Map<WinState, Integer> result;

    public DealerResult() {
        this.result = new LinkedHashMap<>();
    }

    public void addResult(WinState winState, int winStateCount) {
        result.put(winState, winStateCount);
    }

    public Map<WinState, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
