package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GameResult {

    private final Map<String, Boolean> result;

    public GameResult() {
        this.result = new LinkedHashMap<>();
    }

    public void add(String userName, boolean isUserWin) {
        result.put(userName, isUserWin);
    }

    public boolean isUserWin(String userName) {
        return result.getOrDefault(userName, false);
    }

    public int getDealerWinCount() {
        return (int) result.values().stream()
                .filter(isWin -> !isWin)
                .count();
    }

    public int getUserWinCount() {
        return (int) result.values().stream()
                .filter(isWin -> isWin)
                .count();
    }

    public Set<Map.Entry<String, Boolean>> getEntries() {
        return result.entrySet();
    }
}
