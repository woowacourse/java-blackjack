package blackjack.domain;

import java.util.Collections;
import java.util.Map;

public class CardGameResult {
    private final Map<Player, WinningStatus> totalResult;

    public CardGameResult(Map<Player, WinningStatus> totalResult) {
        this.totalResult = totalResult;
    }

    public Map<Player, WinningStatus> getTotalResult() {
        return Collections.unmodifiableMap(totalResult);
    }

    // TODO : cardGameResult.getDealerWinCount();
    // TODO : cardGameResult.getDealerLoseCount();
}
