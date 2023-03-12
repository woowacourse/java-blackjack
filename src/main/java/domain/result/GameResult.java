package domain.result;

import domain.player.Dealer;
import domain.player.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public final class GameResult {

    public GameResult() {

    }

    public Map<String, Integer> makePlayerBetMoneyResults(final Dealer dealer, final Players players) {
        Map<String, Outcome> playerResults = makePlayerResults(dealer, players);
        Map<String, Integer> playerBetMoneyResults = new LinkedHashMap<>();

        players.getPlayers()
                .forEach(player -> {
                    Outcome outcome = playerResults.get(player.getName());
                    player.getBet().calculateBetByOutcome(outcome, player.isBlackJack());
                    playerBetMoneyResults.put(player.getName(), player.getBet().getMoney());
                });
        return playerBetMoneyResults;
    }

    public Map<String, Outcome> makePlayerResults(final Dealer dealer, final Players players) {
        Map<String, Outcome> playerResults = new LinkedHashMap<>();
        players.getPlayers()
                .forEach((player ->
                        playerResults.put(
                                player.getName(),
                                Outcome.decide(player.getScore(), dealer.getScore())
                        )
                ));
        return playerResults;
    }

    public int calculateDealerBetMoneyResult(final Map<String, Integer> results) {
        Map<String, Integer> playerBetMoneyResults = results;
        Integer sum = playerBetMoneyResults.values()
                .stream()
                .reduce(0, Integer::sum);

        return sum * (-1);
    }
}
