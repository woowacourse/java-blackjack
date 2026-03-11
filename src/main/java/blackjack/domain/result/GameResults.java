package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResults {

    private static final GameResultCalculator calculator = new GameResultCalculator();

    private final Map<Player, GameResult> playerResults;

    private GameResults(final Map<Player, GameResult> playerResults) {
        this.playerResults = playerResults;
    }

    public static GameResults create(final Players players, final Dealer dealer) {
        Map<Player, GameResult> playerResults = new LinkedHashMap<>();

        for (Player player : players.players()) {
            GameResult playerResult = calculator.calculate(player.calculateScore(), dealer.calculateScore());
            playerResults.put(player, playerResult);
        }
        return new GameResults(playerResults);
    }

    public Map<GameResult, Integer> dealerResult() {
        Map<GameResult, Integer> dealerResults = new EnumMap<>(GameResult.class);

        for (GameResult playerResult : playerResults.values()) {
            dealerResults.merge(playerResult.reverse(), 1, Integer::sum);
        }
        return dealerResults;
    }

    public Map<Player, GameResult> playerResults() {
        return playerResults;
    }

    public Map<Participant, Integer> calculateProfits(Map<Player, Integer> wagers, Dealer dealer) {
        Map<Participant, Integer> profits = new LinkedHashMap<>();
        profits.put(dealer, 0);

        for (Player player : playerResults.keySet()) {
            Integer profit = profitOf(player, wagers.get(player));
            profits.put(player, profit);
            profits.put(dealer, profits.get(dealer) - profit);
        }
        return profits;
    }

    private Integer profitOf(Player player, Integer wager) {
        GameResult gameResult = playerResults.get(player);
        return gameResult.profitOf(wager);
    }
}
