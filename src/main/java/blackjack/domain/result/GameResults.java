package blackjack.domain.result;

import blackjack.domain.betting.Bettings;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

import java.util.HashMap;
import java.util.Map;

public class GameResults {
    private final Map<Gamer, GameResult> gameResults;

    public GameResults() {
        this(new HashMap<>());
    }

    public GameResults(Map<Gamer, GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public void add(final Gamer gamer, final GameResult gameResult) {
        gameResults.put(gamer, gameResult);
    }

    public int calculateDealerProfit(final Bettings bettings) {
        return -calculatePlayersProfit(bettings);
    }

    private int calculatePlayersProfit(final Bettings bettings) {
        return gameResults.keySet().stream()
                .filter(gamer -> gamer.getClass() == Player.class)
                .map(gamer -> calculateGamerProfit(gamer, bettings))
                .mapToInt(Integer::intValue)
                .sum();
    }

    // TODO: 네이밍이 조금 이상해서 수정해야함
    public int calculateGamerProfit(final Gamer gamer, final Bettings bettings) {
        return bettings.calculateProfit(gamer, gameResults.get(gamer));
    }
}
