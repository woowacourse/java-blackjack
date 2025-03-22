package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResult {
    WIN("승", 1.0),
    LOSE("패", -1.0),
    PUSH("무", 1.0);

    private final String name;
    private final double earningRate;

    GameResult(String name, double earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public static GameResult calculateDealerGameResult(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();
        if (player.isBust() || isDealerWinning(dealer, dealerScore, playerScore)) {
            return WIN;
        }
        if (dealer.isBust() || isDealerLosing(dealerScore, playerScore)) {
            return LOSE;
        }
        return PUSH;
    }

    private static boolean isDealerWinning(Dealer dealer, int dealerScore, int playerScore) {
        return dealerScore > playerScore && !dealer.isBust();
    }

    private static boolean isDealerLosing(int dealerScore, int playerScore) {
        return dealerScore < playerScore;
    }

    public static GameResult calculatePlayerGameResult(Dealer dealer, Player player) {
        return getOppositeResult(calculateDealerGameResult(dealer, player));
    }

    public static GameResult getOppositeResult(GameResult gameResult) {
        if (gameResult == WIN) {
            return LOSE;
        }
        if (gameResult == LOSE) {
            return WIN;
        }
        return PUSH;
    }

    public double getEarningRate(boolean isBlackjack) {
        if (isBlackjack && this == GameResult.WIN) {
            return 1.5;
        }
        return earningRate;
    }

    public String getName() {
        return name;
    }
}
