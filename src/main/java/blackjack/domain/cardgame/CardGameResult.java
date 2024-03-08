package blackjack.domain.cardgame;

import blackjack.domain.player.Player;

import java.util.Collections;
import java.util.Map;

import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.WIN;

public class CardGameResult {
    private final Map<Player, WinningStatus> totalResult;

    public CardGameResult(Map<Player, WinningStatus> totalResult) {
        this.totalResult = totalResult;
    }

    public Map<Player, WinningStatus> getTotalResult() {
        return Collections.unmodifiableMap(totalResult);
    }

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
