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

    public List<GameResult> results() {
        List<GameResult> resultList = new ArrayList<>();
        int dealerScore = getDealerScore();
        games.stream().filter(gameStatus -> !gameStatus.name().equals("딜러")).forEach(gameStatus -> {
            if (isWin(gameStatus, dealerScore)) {
                resultList.add(new GameResult(gameStatus.name(), "승"));
                return;
            }
            resultList.add(new GameResult(gameStatus.name(), "패"));
        });

        return resultList;
    }

    private boolean isWin(GameStatus playerGameStatus, int dealerScore) {
        return playerGameStatus.scoreSum() > dealerScore;
    }

    private int getDealerScore() {
        return games.stream()
                .filter(gameStatus -> gameStatus.name().equals("딜러"))
                .findFirst()
                .map(GameStatus::scoreSum)
                .orElse(0);
    }

    public List<GameStatus> gameStatuses() {
        return List.copyOf(games);
    }
}
