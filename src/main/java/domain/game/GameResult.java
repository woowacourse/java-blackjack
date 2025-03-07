package domain.game;

import domain.participant.Player;
import java.util.Map;

public class GameResult {

    private final Map<Player, Winning> playerWinningResult;

    public GameResult(Map<Player, Winning> playerWinningResult) {
        this.playerWinningResult = playerWinningResult;
    }

    public int countDealerWin() {
        return (int) playerWinningResult.values().stream()
            .filter(winning -> winning == Winning.LOSE)
            .count();
    }

    public int countDealerDraw() {
        return (int) playerWinningResult.values().stream()
            .filter(winning -> winning == Winning.DRAW)
            .count();
    }

    public int countDealerLose() {
        return (int) playerWinningResult.values().stream()
            .filter(winning -> winning == Winning.WIN)
            .count();
    }

    public Map<Player, Winning> getPlayerWinningResult() {
        return playerWinningResult;
    }
}
