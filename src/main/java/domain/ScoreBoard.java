package domain;

import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private static final int BUST_NUMBER = 21;

    private final List<GameResult> results;

    public ScoreBoard() {
        results = new ArrayList<>();
    }

    public void calculateGameResults(List<GameStatus> playersGameStatus, GameStatus dealerGameStatus) {
        int dealerScore = getDealerScore(dealerGameStatus);
        for (GameStatus gameStatus : playersGameStatus) {
            results.add(new GameResult(gameStatus.name(), isWin(gameStatus, dealerScore)));
        }
    }

    public List<GameResult> results() {
        return results;
    }

    private WinningCondition isWin(GameStatus playerGameStatus, int dealerScore) {
        if (playerGameStatus.scoreSum() > BUST_NUMBER) {
            return WinningCondition.LOSE;
        }

        if (dealerScore > BUST_NUMBER) {
            return WinningCondition.WIN;
        }

        if (playerGameStatus.scoreSum() == dealerScore) {
            return WinningCondition.DRAW;
        }

        if (playerGameStatus.scoreSum() < dealerScore) {
            return WinningCondition.LOSE;
        }

        return WinningCondition.WIN;
    }

    private int getDealerScore(GameStatus dealerStatus) {
        return dealerStatus.scoreSum();
    }
}
