package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Results {
    private final Map<String, Map<Score, Integer>> results;
    private final BettingResults bettingResults;

    public Results(BettingResults bettingResults, Players players, Dealer dealer) {
        this.bettingResults = bettingResults;
        this.results = new LinkedHashMap<>();
        results.put(dealer.getName(), initResults());
        for (Player player : players.getPlayers()) {
            results.put(player.getName(), initResults());
        }
    }

    public void executeGame(Players players, Dealer dealer) {
        if (dealer.isBust()) {
            dealerBustCase(players, dealer);
            return;
        }
        for (Player player : players.getPlayers()) {
            fight(player, dealer);
        }
    }

    private void dealerBustCase(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            Score.playerWin(results.get(player.getName()), results.get(dealer.getName()));
            Score.playerWinBetting(bettingResults, player, dealer);
        }
    }

    public void fight(Player player, Dealer dealer) {
        Score playerWinOrLose = Score.comparePlayerDealer(player, dealer);
        if (Score.WIN.equals(playerWinOrLose)) {
            Score.playerWin(results.get(player.getName()), results.get(dealer.getName()));
            Score.playerWinBetting(bettingResults, player, dealer);
        }
        if (Score.LOSE.equals(playerWinOrLose)) {
            Score.dealerWin(results.get(player.getName()), results.get(dealer.getName()));
            Score.playerLoseBetting(bettingResults, player, dealer);
        }
        if (Score.DRAW.equals(playerWinOrLose)) {
            Score.draw(results.get(player.getName()), results.get(dealer.getName()));
            Score.drawBetting(bettingResults, player);
        }
    }

    private Map<Score, Integer> initResults() {
        return Arrays.stream(Score.values())
                .collect(Collectors.toMap(Function.identity(), score -> 0));
    }

    public Map<String, Map<Score, Integer>> getResults() {
        return results;
    }
}
