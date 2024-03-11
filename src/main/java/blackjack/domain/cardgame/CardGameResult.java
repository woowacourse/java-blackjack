package blackjack.domain.cardgame;

import blackjack.domain.player.Player;

import java.util.Collections;
import java.util.Map;

import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.WIN;

public record CardGameResult(Map<Player, WinningStatus> totalResult) {
    @Override
    public Map<Player, WinningStatus> totalResult() {
        return Collections.unmodifiableMap(totalResult);
    }

    public int getDealerWinCount() {
        return (int) totalResult.values()
                .stream()
                .filter(playerWinningStatus -> playerWinningStatus.equals(LOSE))
                .count();
    }

    public int getDealerLoseCount() {
        return (int) totalResult.values()
                .stream()
                .filter(status -> status.equals(WIN))
                .count();
    }
}
