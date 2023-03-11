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
            playerWin(results.get(player.getName()), results.get(dealer.getName()));
            playerWinBetting(player, dealer);
        }
    }

    public void fight(Player player, Dealer dealer) {
        Score playerWinOrLose = comparePlayerDealer(player, dealer);
        if (playerWinOrLose.equals(Score.WIN)) {
            playerWin(results.get(player.getName()), results.get(dealer.getName()));
            playerWinBetting(player, dealer);
        }
        if (playerWinOrLose.equals(Score.LOSE)) {
            dealerWin(results.get(player.getName()), results.get(dealer.getName()));
            playerLoseBetting(player, dealer);
        }
        if (playerWinOrLose.equals(Score.DRAW)) {
            draw(results.get(player.getName()), results.get(dealer.getName()));
            drawBetting(player);
        }
    }

    private Score comparePlayerDealer(Player player, Dealer dealer) {
        if (player.isBust() || player.getScoreSum() - dealer.getScoreSum() < 0) {
            return Score.LOSE;
        }
        if (dealer.isBust() || player.getScoreSum() - dealer.getScoreSum() > 0) {
            return Score.WIN;
        }
        return Score.DRAW;
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

    private void playerWinBetting(Player player, Dealer dealer) {
        if (player.getHandCards().isBlackJack()) {
            bettingResults.plusBettingResult(player, bettingResults.getParticipantBet(player).getAmount() / 2);
        }
        bettingResults.plusBettingResult(dealer, -bettingResults.getParticipantBet(player).getAmount());
    }

    private void playerLoseBetting(Player player, Dealer dealer) {
        bettingResults.plusBettingResult(dealer, bettingResults.getParticipantBet(player).getAmount());
        bettingResults.plusBettingResult(player, -(2 * bettingResults.getParticipantBet(player).getAmount()));
    }

    private void drawBetting(Player player) {
        bettingResults.plusBettingResult(player, -bettingResults.getParticipantBet(player).getAmount());
    }

    private Map<Score, Integer> initResults() {
        return Arrays.stream(Score.values())
                .collect(Collectors.toMap(Function.identity(), score -> 0));
    }

    public Map<String, Map<Score, Integer>> getResults() {
        return results;
    }
}
