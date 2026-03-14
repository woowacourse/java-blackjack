package blackjack.domain.result;

import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static blackjack.domain.result.GameResult.*;

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
            dealerResults.merge(reverse(playerResult), 1, Integer::sum);
        }
        return dealerResults;
    }

    public Map<Player, GameResult> playerResults() {
        return playerResults;
    }

    public Money profitOf(Player player, Money wager) {
        GameResult gameResult = playerResults.get(player);
        return gameResult.profitOf(wager);
    }

    private static GameResult resolveGameResult(Dealer dealer, Player player) {
        return calculator.calculate(player, dealer);
    }

    private GameResult reverse(GameResult playerResult) {
        if (playerResult == WIN || playerResult == BLACKJACK) {
            return LOSE;
        }
        if (playerResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
