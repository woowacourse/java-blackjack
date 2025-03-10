package domain.game;

import java.util.List;

public enum GameResult {

    WIN("승"),
    BLACKJACK_WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public int countGameResultFromDealer(List<GameResult> gameResults) {
        if (this == GameResult.WIN || this == GameResult.BLACKJACK_WIN) {
            return (int) gameResults.stream()
                    .filter(gameResult -> gameResult == GameResult.LOSE)
                    .count();
        }
        if (this == GameResult.LOSE) {
            return (int) gameResults.stream()
                    .filter(gameResult -> gameResult == GameResult.WIN)
                    .count();
        }
        return (int) gameResults.stream()
                .filter(gameResult -> gameResult == GameResult.DRAW)
                .count();
    }

    public String getResult() {
        return result;
    }
}
