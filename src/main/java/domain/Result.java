package domain;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private final Map<String, GameResult> gameResult;

    public Result(Dealer dealer, Players players) {
        gameResult = new HashMap<>();
        calculate(dealer, players);
    }

    private void calculate(Dealer dealer, Players players) {
        int dealerScore = dealer.getTotalScore();

        for (Player player : players.getPlayers()) {
            calculateWinLose(dealer, dealerScore, player);
        }
    }

    private void calculateWinLose(Dealer dealer, int dealerScore, Player player) {
        int playerScore = player.getTotalScore();
        draw(dealer, dealerScore, player, playerScore);
        lose(dealerScore, player, playerScore);
        win(dealer, dealerScore, player, playerScore);
    }

    private void win(Dealer dealer, int dealerScore, Player player, int playerScore) {
        if (dealer.isBust() || dealerScore < playerScore) {
            gameResult.put(player.getName().getName(), GameResult.WIN);
        }
    }

    private void lose(int dealerScore, Player player, int playerScore) {
        if (player.isBust() || dealerScore > playerScore) {
            gameResult.put(player.getName().getName(), GameResult.LOSE);
        }
    }

    private void draw(Dealer dealer, int dealerScore, Player player, int playerScore) {
        if (dealer.isBust() && player.isBust() || dealerScore == playerScore) {
            gameResult.put(player.getName().getName(), GameResult.DRAW);
        }
    }

    public Map<String, GameResult> getResult() {
        return gameResult;
    }

}
