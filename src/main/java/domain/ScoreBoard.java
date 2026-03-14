package domain;

import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    public static List<GameResult> calculateGameResults(List<GameStatus> playersGameStatus, GameStatus dealerGameStatus) {
        List<GameResult> results = new ArrayList<>();
        for (GameStatus gameStatus : playersGameStatus) {
            WinningCondition condition = WinningCondition.from(gameStatus, dealerGameStatus);
            results.add(new GameResult(gameStatus.name(), condition));
        }
        return results;
    }
}
