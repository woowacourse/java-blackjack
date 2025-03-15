package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.Arrays;
import java.util.List;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResult(final String description) {
        this.description = description;
    }

    public static List<GameResult> getAllGameResults() {
        return Arrays.stream(GameResult.values()).toList();
    }

    public static GameResult findByScores(int score, int compareScore) {
        if (score > compareScore) {
            return WIN;
        }
        if (score < compareScore) {
            return LOSE;
        }
        return DRAW;
    }

    public static GameResult calculateResult(final Dealer dealer, final Player player) {
        if (dealer.isBust() && player.isBust()) {
            return GameResult.DRAW;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return player.calculateGameResult(dealer.calculateScore());
    }

    public String getDescription() {
        return description;
    }
}
