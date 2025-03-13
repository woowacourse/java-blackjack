package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    PUSH("무"),
    NONE("없음");

    private final String name;

    GameResult(String name) {
        this.name = name;
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

    public static GameResult getOppositeResult(GameResult gameResult) {
        if (gameResult == WIN) {
            return LOSE;
        }
        if (gameResult == LOSE) {
            return WIN;
        }
        return PUSH;
    }

    public static GameResult calculatePlayerGameResult(Dealer dealer, Player player) {
        return getOppositeResult(calculateDealerGameResult(dealer, player));
    }

    public String getName() {
        return name;
    }

    private static boolean isDealerLosing(int dealerScore, int playerScore) {
        return dealerScore < playerScore;
    }

    private static boolean isDealerWinning(Dealer dealer, int dealerScore, int playerScore) {
        return dealerScore > playerScore && !dealer.isBust();
    }
}
