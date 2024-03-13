package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.WIN;

public record CardGameResult(Map<Player, WinningStatus> totalResult) {
    public static CardGameResult of(final Dealer dealer, final List<Player> players) {
        return new CardGameResult(
                players.stream()
                        .collect(Collectors.toMap(
                                player -> player,
                                player -> WinningStatus.doesPlayerWin(dealer, player),
                                (key, value) -> key,
                                LinkedHashMap::new
                        )));
    }

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
