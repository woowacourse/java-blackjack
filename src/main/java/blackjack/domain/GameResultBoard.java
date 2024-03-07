package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResultBoard {
    private final Map<String, GameResult> resultBoard = new HashMap<>();

    public GameResultBoard(Dealer dealer, List<Player> players) {
        int dealerScore = dealer.getScore();
        for (Player player : players) {
            String playerName = player.getName();
            int playerScore = player.getScore();
            GameResult gameResult = calculateGameResult(playerScore, dealerScore);
            resultBoard.put(playerName, gameResult);
        }
    }

    private GameResult calculateGameResult(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public GameResult getGameResult(Player player) {
        return resultBoard.get(player.getName());
    }
}
