package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResult {

    WIN("승", 1.0),
    LOSE("패", -1),
    DRAW("무", 0),
    BLACKJACK("승", 1.5);

    public static final int BLACKJACK_SCORE = 21;

    private final String description;
    private final double profit;

    GameResult(String description, double profit) {
        this.description = description;
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public double getProfit() {
        return profit;
    }

    public static GameResult judge(Dealer dealer, Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BLACKJACK;
        }
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return compareScore(dealer.getScore(), player.getScore());
    }

    private static GameResult compareScore(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public GameResult reverse() {
        if (this == BLACKJACK) {
            return LOSE;
        }
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

}
