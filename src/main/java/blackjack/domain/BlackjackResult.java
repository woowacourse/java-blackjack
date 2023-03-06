package blackjack.domain;

import blackjack.util.WinningResult;
import java.util.List;
import java.util.Map;

public class BlackjackResult {

    private final Map<Player, WinningResult> playerResult;
    private final List<WinningResult> dealerResult;

    public BlackjackResult(Map<Player, WinningResult> playerResult, List<WinningResult> dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public Map<Player, WinningResult> getPlayerResult() {
        return playerResult;
    }

    public List<WinningResult> getDealerResult() {
        return dealerResult;
    }
}
