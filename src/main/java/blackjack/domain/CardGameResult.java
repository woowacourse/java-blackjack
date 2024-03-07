package blackjack.domain;

import java.util.Collections;
import java.util.Map;

import static blackjack.domain.WinningStatus.LOSE;
import static blackjack.domain.WinningStatus.WIN;

public class CardGameResult {
    private final Map<Player, WinningStatus> totalResult;

    public CardGameResult(Map<Player, WinningStatus> totalResult) {
        this.totalResult = totalResult;
    }

    public Map<Player, WinningStatus> getTotalResult() {
        return Collections.unmodifiableMap(totalResult);
    }

    // TODO: 어지러움
    public int getDealerWinCount() {
        return (int) totalResult.values()
                .stream()
                .filter(playerWinningstatus -> playerWinningstatus.equals(LOSE))
                .count();
    }

    public int getDealerLoseCount() {
        return (int) totalResult.values()
                .stream()
                .filter(status -> status.equals(WIN))
                .count();
    }
}
