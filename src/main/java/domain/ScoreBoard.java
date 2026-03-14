package domain;

import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private static final int CHANGE_NEGATIVE = -1;

    public static List<GameResult> calculateGameResults(List<GameStatus> playersGameStatus,
                                                        GameStatus dealerGameStatus) {
        List<GameResult> results = new ArrayList<>();
        for (GameStatus gameStatus : playersGameStatus) {
            WinningCondition condition = WinningCondition.from(gameStatus, dealerGameStatus);
            results.add(new GameResult(gameStatus.name(), condition));
        }
        return results;
    }

    public static int calculateEarningPrize(GameResult gameResult, Bet bet) {
        WinningCondition condition = gameResult.winningCondition();
        return (int) condition.earning(bet.bettingAmount(gameResult.name()));
    }

    public static int calculateDealerProfit(List<GameResult> gameResults, Bet bet) {
        int playerProfit = 0;
        for (GameResult gameResult : gameResults) {
            WinningCondition condition = gameResult.winningCondition();
            playerProfit += (int) condition.earning(bet.bettingAmount(gameResult.name()));
        }
        return playerProfit * CHANGE_NEGATIVE;
    }
}
