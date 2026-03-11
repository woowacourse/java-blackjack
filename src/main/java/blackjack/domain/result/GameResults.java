package blackjack.domain.result;

import blackjack.domain.money.Money;
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
        Map<Player, GameResult> gameResults = new LinkedHashMap<>();

        for (Player player : players.players()) {
            GameResult gameResult = resolveGameResult(dealer, player);
            gameResults.put(player, gameResult);
        }
        return new GameResults(gameResults);
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

    public Map<Participant, Money> calculateProfits(Map<Player, Money> wagers, Dealer dealer) {
        Map<Participant, Money> profits = new LinkedHashMap<>();
        profits.put(dealer, new Money(0));

        for (Player player : playerResults.keySet()) {
            Money playerProfit = profitOf(player, wagers.get(player));
            profits.put(player, playerProfit);
            profits.put(dealer, profits.get(dealer).add(playerProfit.negate()));
        }
        return profits;
    }

    private static GameResult resolveGameResult(Dealer dealer, Player player) {
        return calculator.calculate(player, dealer);
    }

    private Money profitOf(Player player, Money wager) {
        GameResult gameResult = playerResults.get(player);
        return gameResult.profitOf(wager);
    }
}
