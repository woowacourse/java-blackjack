package domain.game;

import static domain.game.Winning.LOSE;
import static domain.game.Winning.PUSH;
import static domain.game.Winning.WIN;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Users;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<String, Winning> playerResults;
    private final Map<Winning, Integer> dealerResult;

    private GameResult(final Map<String, Winning> playerResults,
        final Map<Winning, Integer> dealerResult) {
        this.playerResults = playerResults;
        this.dealerResult = dealerResult;
    }

    public static GameResult of(final Users users) {
        Map<String, Winning> playerResults = calculatePlayerResults(users);
        Map<Winning, Integer> dealerResult = calculateDealerResult(playerResults);
        return new GameResult(playerResults, dealerResult);
    }

    private static Map<String, Winning> calculatePlayerResults(final Users users) {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();
        Map<String, Winning> playerResults = new LinkedHashMap<>();
        for (Player player : players) {
            Winning playerResult = player.match(dealer);
            playerResults.put(player.getName(), playerResult);
        }
        return Collections.unmodifiableMap(playerResults);
    }

    private static Map<Winning, Integer> calculateDealerResult(final Map<String, Winning> playerResults) {
        Map<Winning, Integer> dealerResult = new EnumMap<>(Winning.class);
        for (Winning playerResult : playerResults.values()) {
            convertAndPutResult(playerResult, dealerResult);
        }
        return Collections.unmodifiableMap(dealerResult);
    }

    private static void convertAndPutResult(final Winning playerResult,
        final Map<Winning, Integer> dealerResult) {
        Map<Winning, Winning> converter = setUpResultConverter();
        Winning convertedResult = converter.get(playerResult);
        dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
    }

    private static Map<Winning, Winning> setUpResultConverter() {
        Map<Winning, Winning> converter = new EnumMap<>(Winning.class);
        converter.put(WIN, LOSE);
        converter.put(LOSE, WIN);
        converter.put(PUSH, PUSH);
        return converter;
    }

    public Map<String, Winning> getPlayerResults() {
        return playerResults;
    }

    public Map<Winning, Integer> getDealerResult() {
        return dealerResult;
    }
}
