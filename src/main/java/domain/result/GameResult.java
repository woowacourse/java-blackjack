package domain.result;

import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public final class GameResult {

    private static final int DEALER_MONEY_MULTIPLY_NUMBER = -1;

    private final Map<Player, Outcome> gameResult;

    public GameResult(final Dealer dealer, final Players players) {
        gameResult = initPlayerResults(dealer, players);
    }

    private Map<Player, Outcome> initPlayerResults(final Dealer dealer, final Players players) {
        Map<Player, Outcome> playerResults = new LinkedHashMap<>();
        players.getPlayers()
                .forEach(player -> {
                    playerResults.put(player, Outcome.decide(player.getScore(), dealer.getScore()));
                });
        return playerResults;
    }

    public Outcome getOutcomeByPlayer(final Player player) {
        if (!gameResult.containsKey(player)) {
            throw new IllegalArgumentException("[Error] 해당 플레이어는 존재하지 않습니다.");
        }
        return gameResult.get(player);
    }

    public Map<String, Integer> makePlayerBetMoneyResults(final Players players) {
        Map<String, Integer> playerBetMoneyResults = new LinkedHashMap<>();

        players.getPlayers()
                .forEach(player -> {
                    Outcome outcome = gameResult.get(player);
                    player.getBet().calculateBetByOutcome(outcome, player.isBlackJack());
                    playerBetMoneyResults.put(player.getName(), player.getBet().getMoney());
                });
        return playerBetMoneyResults;
    }

    public int calculateDealerBetMoneyResult(final Map<String, Integer> results) {
        Map<String, Integer> playerBetMoneyResults = results;
        Integer sum = playerBetMoneyResults.values()
                .stream()
                .reduce(0, Integer::sum);

        return sum * (DEALER_MONEY_MULTIPLY_NUMBER);
    }
}
