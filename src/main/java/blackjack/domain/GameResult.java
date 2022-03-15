package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public static GameResult findPlayerResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return compareScore(player.calculateScore(), dealer.calculateScore());
    }

    public static GameResult compareScore(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public static GameResult findDealerResult(GameResult playerResult) {
        if (playerResult == WIN) {
            return LOSE;
        }
        if (playerResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
