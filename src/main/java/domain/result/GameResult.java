package domain.result;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.OutputView;

public class GameResult {

    private static final String COLON = ": ";
    private static final String SPACE = " ";
    private Map<ResultType, Integer> winningResultOfDealer;
    private Map<Player, ResultType> winningResultOfPlayers;

    public static GameResult of(Dealer dealer, Players players) {
        return new GameResult(dealer, players);
    }

    private GameResult(Dealer dealer, Players players) {
        winningResultOfDealer = new LinkedHashMap<>();

        Arrays.stream(ResultType.values())
                .forEach(winningResult ->
                        winningResultOfDealer.put(winningResult, 0));

        collectResult(dealer, players);
    }

    private void collectResult(Dealer dealer, Players players) {
        winningResultOfPlayers = players.decideWinner(dealer);
        winningResultOfPlayers.forEach(
                (player, winningResult) -> winningResultOfDealer.computeIfPresent(winningResult.reverse(),
                        (key, value) -> value + 1));
    }

    public String getTotalWinningResults() {
        return getDealerWinningResult() + OutputView.NEWLINE + getPlayersWinningResult();
    }

    private String getDealerWinningResult() {
        return Dealer.DEALER + COLON
                + winningResultOfDealer.entrySet()
                .stream()
                .map(winningResult -> winningResult.getValue() + winningResult.getKey().getResult())
                .collect(Collectors.joining(SPACE));
    }

    private String getPlayersWinningResult() {
        return winningResultOfPlayers.entrySet()
                .stream()
                .map(player -> player.getKey().getName() + COLON + player.getValue().getResult())
                .collect(Collectors.joining(OutputView.NEWLINE));
    }
}
