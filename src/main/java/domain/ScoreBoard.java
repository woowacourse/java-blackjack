package domain;

import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<GameStatus> games;

    public ScoreBoard() {
        this.games = new ArrayList<>();
    }

    public void record(GameStatus status) {
        games.add(status);
    }

    public List<GameResult> playerResults() {;
        int dealerScore = getDealerScore();

        return games.stream()
                .filter(this::isPlayer)
                .map(gameStatus -> getGameResult(gameStatus, dealerScore))
                .toList();
    }

    private boolean isPlayer(GameStatus gameStatus) {
        return !gameStatus.name().equals("딜러");
    }

    private GameResult getGameResult(GameStatus gameStatus, int dealerScore) {
        if (isWin(gameStatus, dealerScore)) {
            return new GameResult(gameStatus.name(), "승");
        }
        return new GameResult(gameStatus.name(), "패");
    }

    private void resultSort() {
        GameStatus lastState = games.removeLast();
        games.addFirst(lastState);
    }

    private boolean isWin(GameStatus playerGameStatus, int dealerScore) {
        if (playerGameStatus.scoreSum() > 21) {
            return false;
        }
        return playerGameStatus.scoreSum() > dealerScore;
    }

    private int getDealerScore() {
        return games.stream()
                .filter(gameStatus -> isPlayer(gameStatus))
                .findFirst()
                .map(GameStatus::scoreSum)
                .orElse(0);
    }

    public List<GameStatus> gameStatuses() {
        resultSort();
        return List.copyOf(games);
    }
}
