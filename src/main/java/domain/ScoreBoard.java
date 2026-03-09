package domain;

import domain.constant.BlackJackRule;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<PlayedGameResult> gameResults;

    public ScoreBoard() {
        this.gameResults = new ArrayList<>();
    }

    void record(PlayedGameResult playedGameResult) {
        gameResults.add(playedGameResult);
    }

    List<PlayedGameResult> playerGameResults() {
        return gameResults.stream()
                .filter(this::isPlayerResult)
                .toList();
    }

    private boolean isPlayerResult(PlayedGameResult result) {
        return !result.infos()
                .name()
                .equals("딜러");
    }

    PlayedGameResult dealerGameResult() {
        return gameResults.stream()
                .filter(result -> !isPlayerResult(result))
                .findFirst()
                .orElseThrow();
    }

    private boolean isWin(GameStatus playerGameStatus, int dealerScore) {
        if (playerGameStatus.scoreSum() > BlackJackRule.BUST_NUMBER.value()) {
            return false;
        }

        if (dealerScore > BlackJackRule.BUST_NUMBER.value()) {
            return true;
        }

        return playerGameStatus.scoreSum() > dealerScore;
    }

    public PlayedGameResult dealerResult() {
        return null;
    }
}
