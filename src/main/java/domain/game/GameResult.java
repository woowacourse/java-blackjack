package domain.game;

import domain.participant.Player;
import java.util.Map;

public class GameResult {

    private final Map<Player, Winning> playerWinningResult;

    public GameResult(Map<Player, Winning> playerWinningResult) {
        this.playerWinningResult = playerWinningResult;
    }

    public int countDealerWin() {
        return convertWinning(Winning.LOSE);
    }

    public int countDealerDraw() {
        return convertWinning(Winning.DRAW);
    }

    public int countDealerLose() {
        return convertWinning(Winning.WIN) + convertWinning(Winning.BLACKJACK);
    }

    private int convertWinning(Winning winningStatus) {
        return (int) playerWinningResult.values().stream()
                .filter(winning -> winning == winningStatus)
                .count();
    }

    public Map<Player, Winning> getPlayerWinningResult() {
        return playerWinningResult;
    }
}
