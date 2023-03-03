package domain;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private final Map<String, GameResult> gameResult;

    public Result(Participant dealer, Players players) {
        gameResult = new HashMap<>();
        calculate(dealer, players);
    }

    private void calculate(Participant dealer, Players players) {
        for (Participant player : players.getPlayers()) {
            calculateWinLose(dealer, player);
        }
    }

    private void calculateWinLose(Participant dealer, Participant player) {
        win(dealer, player);
        lose(dealer, player);
        draw(dealer, player);
    }

    private void win(Participant dealer, Participant player) {
        if (!player.isBust() && (dealer.isBust() || dealer.getTotalScore() < player.getTotalScore())) {
            gameResult.put(player.getName().getValue(), GameResult.WIN);
        }
    }

    private void lose(Participant dealer, Participant player) {
        if (!dealer.isBust() && (player.isBust() || dealer.getTotalScore() > player.getTotalScore())) {
            gameResult.put(player.getName().getValue(), GameResult.LOSE);
        }
    }

    private void draw(Participant dealer, Participant player) {
        if (dealer.isBust() && player.isBust() || dealer.getTotalScore() == player.getTotalScore()) {
            gameResult.put(player.getName().getValue(), GameResult.DRAW);
        }
    }

    public Map<String, GameResult> getResult() {
        return gameResult;
    }

}
