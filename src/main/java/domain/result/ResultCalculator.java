package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResultCalculator {
    private final Map<String, Map<Score, Integer>> results;

    public ResultCalculator(Players players, Dealer dealer) {
        this.results = new LinkedHashMap<>();
        results.put(dealer.getName(), initResults());
        for (Player player : players.getPlayers()) {
            results.put(player.getName(), initResults());
        }
    }

    public void executeGame(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            fight(player, dealer);
        }
    }

    public void fight(Player player, Dealer dealer) {
        int playerWinOrLose = player.getHandCards().checkBust() - dealer.getHandCards().checkBust();
        if (playerWinOrLose > 0) {
            playerWin(results.get(player.getName()), results.get(dealer.getName()));
        }
        if (playerWinOrLose < 0) {
            dealerWin(results.get(player.getName()), results.get(dealer.getName()));
        }
        if (playerWinOrLose == 0) {
            draw(results.get(player.getName()), results.get(dealer.getName()));
        }
    }

    private void playerWin(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.computeIfPresent(Score.WIN, (k, v) -> v + 1);
        dealerResult.computeIfPresent(Score.LOSE, (k, v) -> v + 1);
    }

    private void dealerWin(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.computeIfPresent(Score.LOSE, (k, v) -> v + 1);
        dealerResult.computeIfPresent(Score.WIN, (k, v) -> v + 1);
    }

    private void draw(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.computeIfPresent(Score.DRAW, (k, v) -> v + 1);
        dealerResult.computeIfPresent(Score.DRAW, (k, v) -> v + 1);
    }

    private Map<Score, Integer> initResults() {
        return Arrays.stream(Score.values())
                .collect(Collectors.toMap(Function.identity(), score -> 0));
    }

    public Map<String, Map<Score, Integer>> getResults() {
        return results;
    }
}
