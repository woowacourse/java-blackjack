package blackjack.domain.result;

import blackjack.domain.player.Score;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResultBoard {
    private final Map<String, GameResult> resultBoard = new HashMap<>();

    public GameResultBoard(Dealer dealer, List<Player> players) {
        Score dealerScore = dealer.getScore();
        for (Player player : players) {
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
                .filter(playerResult -> playerResult.equals(GameResult.LOSE))
                .count();
    }

    private int getDealerLoseCount() {
        return (int) resultBoard.values().stream()
                .filter(playerResult -> playerResult.equals(GameResult.WIN))
                .count();
    }

    private int getDealerDrawCount() {
        return (int) resultBoard.values().stream()
                .filter(playerResult -> playerResult.equals(GameResult.DRAW))
                .count();
    }
}
