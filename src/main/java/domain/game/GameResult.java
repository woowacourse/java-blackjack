package domain.game;

import java.util.List;

public enum GameResult {

    WIN,
    BLACKJACK_WIN,
    LOSE,
    DRAW;

    public int countGameResultFromDealer(List<GameResult> gameResults) {
        if (this == GameResult.WIN || this == GameResult.BLACKJACK_WIN) {
            return (int) gameResults.stream()
                    .filter(gameResult -> gameResult == GameResult.LOSE)
                    .count();
        }
        if (this == GameResult.LOSE) {
            return (int) gameResults.stream()
                    .filter(gameResult -> gameResult == GameResult.WIN || gameResult == GameResult.BLACKJACK_WIN)
                    .count();
        }
        return (int) gameResults.stream()
                .filter(gameResult -> gameResult == GameResult.DRAW)
                .count();
    }
}
