package blackjack.domain;

import java.util.List;
import java.util.Map;

public class GameResult {

    private Map<WinResult, Integer> dealerResult;
    private List<Result> playersResult;

    public GameResult(Map<WinResult, Integer> dealerResult, List<Result> playersResult) {
        this.dealerResult = dealerResult;
        this.playersResult = playersResult;
    }

    public Map<WinResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public List<Result> getPlayersResult() {
        return playersResult;
    }
}
