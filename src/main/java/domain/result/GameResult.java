package domain.result;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import domain.rule.PlayerResultRule;
import domain.rule.Rules;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.OutputView;

public class GameResult {

    private static final String COLON = ": ";
    private static final String SPACE = " ";
    private Map<ResultType, Integer> resultOfDealer;
    private Map<Player, ResultType> resultOfPlayers;

    public static GameResult of(Dealer dealer, Players players) {
        return new GameResult(dealer, players);
    }

    private GameResult(Dealer dealer, Players players) {
        resultOfDealer = new LinkedHashMap<>();

        Arrays.stream(ResultType.values())
                .forEach(winningResult ->
                        resultOfDealer.put(winningResult, 0));

        collectResult(dealer, players);
    }

    private void collectResult(Dealer dealer, Players players) {
        Rules rules = new Rules(Arrays.asList(PlayerResultRule.values()));

        resultOfPlayers = players.decideWinner(dealer, rules);
        resultOfPlayers.forEach(
                (player, result) -> resultOfDealer.computeIfPresent(result.reverse(),
                        (key, value) -> value + 1));
    }

    public String getTotalResults() {
        return getDealerResult() + OutputView.NEWLINE + getPlayersResult();
    }

    private String getDealerResult() {
        return Dealer.DEALER + COLON
                + resultOfDealer.entrySet()
                .stream()
                .map(result -> result.getValue() + result.getKey().getResult())
                .collect(Collectors.joining(SPACE));
    }

    private String getPlayersResult() {
        return resultOfPlayers.entrySet()
                .stream()
                .map(player -> player.getKey().getName() + COLON + player.getValue().getResult())
                .collect(Collectors.joining(OutputView.NEWLINE));
    }
}
