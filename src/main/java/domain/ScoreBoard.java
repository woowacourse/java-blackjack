package domain;

import common.Constants;
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

    public List<GameResult> playerResults() {
        List<GameResult> resultList = new ArrayList<>();
        games.forEach(gameStatus -> addPlayerResult(gameStatus, resultList));

        return resultList;
    }

    private void addPlayerResult(GameStatus gameStatus, List<GameResult> resultList) {
        if (!gameStatus.name().equals(Constants.DEALER_NAME)) {
            addResults(gameStatus, resultList);
        }
    }

    private void addResults(GameStatus gameStatus, List<GameResult> resultList) {
        int dealerScore = getDealerScore();
        if (isWin(gameStatus, dealerScore)) {
            resultList.add(new GameResult(gameStatus.name(), Constants.WIN));
            return;
        }
        resultList.add(new GameResult(gameStatus.name(), Constants.LOSE));
    }

    private boolean isDealer(GameStatus gameStatus) {
        return gameStatus.name().equals(Constants.DEALER_NAME);
    }

    private void resultSort() {
        GameStatus lastState = games.removeLast();
        games.addFirst(lastState);
    }

    private boolean isWin(GameStatus playerGameStatus, int dealerScore) {
        if (playerGameStatus.scoreSum() > Constants.BUST_NUMBER) {
            return false;
        }

        if (dealerScore > Constants.BUST_NUMBER) {
            return true;
        }

        return playerGameStatus.scoreSum() > dealerScore;
    }

    private int getDealerScore() {
        return games.stream()
                .filter(this::isDealer)
                .findFirst()
                .map(GameStatus::scoreSum)
                .orElse(Constants.DEFAULT_HAND_SCORE);
    }

    public List<GameStatus> gameStatuses() {
        resultSort();
        return List.copyOf(games);
    }
}
