package domain;

import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final int BUST_NUMBER = 21;
    private static final int DEFAULT_HAND_SCORE = 0;

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
        if (gameStatus.role().equals(ParticipantsRole.PLAYER)) {
            addResults(gameStatus, resultList);
        }
    }

    private void addResults(GameStatus gameStatus, List<GameResult> resultList) {
        int dealerScore = getDealerScore();
        if (isWin(gameStatus, dealerScore)) {
            resultList.add(new GameResult(gameStatus.name(), WIN));
            return;
        }
        resultList.add(new GameResult(gameStatus.name(), LOSE));
    }

    private boolean isDealer(GameStatus gameStatus) {
        return gameStatus.role().equals(ParticipantsRole.DEALER);
    }

    private void resultSort() {
        GameStatus lastState = games.removeLast();
        games.addFirst(lastState);
    }

    private boolean isWin(GameStatus playerGameStatus, int dealerScore) {
        if (playerGameStatus.scoreSum() > BUST_NUMBER) {
            return false;
        }

        if (dealerScore > BUST_NUMBER) {
            return true;
        }

        return playerGameStatus.scoreSum() > dealerScore;
    }

    private int getDealerScore() {
        return games.stream()
                .filter(this::isDealer)
                .findFirst()
                .map(GameStatus::scoreSum)
                .orElse(DEFAULT_HAND_SCORE);
    }

    public List<GameStatus> gameStatuses() {
        resultSort();
        return List.copyOf(games);
    }
}
