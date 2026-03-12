package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    BLACKJACK("승")
    ;

    public static final int BLACKJACK_SCORE = 21;

    private final String description;

    GameResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public static GameResult judge(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BLACKJACK;
        }
        return compareScore(dealer.getScore(), player.getScore());
    }

    private static GameResult compareScore(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) return WIN;
        if (playerScore < dealerScore) return LOSE;
        return DRAW;
    }

    public GameResult reverse() {
        if (this == BLACKJACK) return LOSE;
        if (this == WIN) return LOSE;
        if (this == LOSE) return WIN;
        return DRAW;
    }

}
