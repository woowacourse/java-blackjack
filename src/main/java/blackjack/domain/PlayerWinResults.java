package blackjack.domain;

import static blackjack.domain.WinResult.LOSE;
import static blackjack.domain.WinResult.PUSH;
import static blackjack.domain.WinResult.WIN;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerWinResults {

    private final Map<String, WinResult> results;

    public PlayerWinResults() {
        this.results = new LinkedHashMap<>();
    }

    public void addResultByPlayerName(String playerName, WinResult playerResult) {
        results.put(playerName, playerResult);
    }

    public int countDealerWins() {
        return computeWinResultCount(LOSE);
    }

    public int countDealerPushes() {
        return computeWinResultCount(PUSH);
    }

    public int countDealerLoses() {
        return computeWinResultCount(WIN);
    }

    private int computeWinResultCount(WinResult winResult) {
        return (int) results.values()
                .stream()
                .filter(result -> result == winResult)
                .count();
    }

    public Map<String, WinResult> getResults() {
        return results;
    }
}
