package domain.analyzer;

import domain.dealer.Dealer;
import domain.player.Player;

public enum GameResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    ;

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public String displayName() {
        return result;
    }

    public static GameResult judge(Dealer dealer, Player player) {
        if (player.isBusted()) {
            return LOSS;
        }

        if (dealer.isBusted()) {
            return WIN;
        }

        return compareScore(dealer.getResultScore(), player.getResultScore());
    }

    public static GameResult compareScore(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return LOSS;
        }

        if (dealerScore == playerScore) {
            return DRAW;
        }

        return WIN;
    }

    public GameResult reverseResult() {
        if (this == WIN) {
            return LOSS;
        }

        if (this == LOSS) {
            return WIN;
        }

        return this;
    }

}
