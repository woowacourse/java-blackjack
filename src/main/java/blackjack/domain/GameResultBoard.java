package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class GameResultBoard {
    private final Map<String, GameResult> resultBoard = new HashMap<>();

    public GameResultBoard(Dealer dealer, Players players) {
        Score dealerScore = dealer.getScore();
        for (Player player : players.getPlayers()) {
            String playerName = player.getName();
            Score playerScore = player.getScore();
            GameResult gameResult = GameResult.calculatePlayerResult(playerScore, dealerScore);
            resultBoard.put(playerName, gameResult);
        }
    }

    public GameResult getGameResult(Player player) {
        return resultBoard.get(player.getName());
    }

    public Map<GameResult, Integer> getDealerResult() {
        return Map.of(
                GameResult.WIN, getDealerWinCount(),
                GameResult.DRAW, getDealerDrawCount(),
                GameResult.LOSE, getDealerLoseCount()
        );
    }

    private int getDealerWinCount() {
        return (int) resultBoard.values().stream()
                .filter(GameResult::isLose)
                .count();
    }

    private int getDealerLoseCount() {
        return (int) resultBoard.values().stream()
                .filter(GameResult::isWin)
                .count();
    }

    private int getDealerDrawCount() {
        return (int) resultBoard.values().stream()
                .filter(GameResult::isDraw)
                .count();
    }
}
