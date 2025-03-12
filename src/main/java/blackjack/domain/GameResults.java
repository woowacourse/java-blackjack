package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResults {

    private final Map<Player, GameResult> gameResults;

    public GameResults(final Dealer dealer, final List<Gambler> gamblers) {
        gameResults = gamblers.stream()
                .collect(Collectors.toMap(
                        gambler -> gambler,
                        gambler -> GameResult.evaluateGameResult(dealer, gambler)
                ));
    }

    public GameResult getGameResult(final Player player) {
        return gameResults.get(player);
    }

    public int getDealerWin() {
        return (int) gameResults.entrySet().stream()
                .filter(entry -> entry.getValue().equals(GameResult.LOSE)
                ).count();
    }

    public int getDealerLose() {
        return (int) gameResults.entrySet().stream()
                .filter(entry -> entry.getValue().equals(GameResult.WIN)
                ).count();
    }

    public int getDealerDraw() {
        return (int) gameResults.entrySet().stream()
                .filter(entry -> entry.getValue().equals(GameResult.DRAW)
                ).count();
    }
}
