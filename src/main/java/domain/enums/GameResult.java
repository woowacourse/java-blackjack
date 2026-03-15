package domain.enums;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GameResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0),
    ;

    private final double profitRatio;

    GameResult(double profitRatio) {
        this.profitRatio = profitRatio;
    }

    public static GameResult calculatePlayerResult(Player player, Dealer dealer) {
        if (player.isBlackjack() || dealer.isBlackjack()) {
            return calculateBlackjackResult(player, dealer);
        }
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return calculateScoreResult(player, dealer);
    }

    private static GameResult calculateBlackjackResult(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        return LOSE;
    }

    private static GameResult calculateScoreResult(Player player, Dealer dealer) {
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return DRAW;
        }
        return LOSE;
    }


    public static Map<GameResult, Integer> calculateDealerResult(List<GameResult> playerGameResults) {
        Map<GameResult, Integer> results = new HashMap<>();
        playerGameResults.forEach(
                result -> {
                    GameResult dealerGameResult = GameResult.getOpposite(result);
                    results.put(dealerGameResult, results.getOrDefault(dealerGameResult, 0) + 1);
                }
        );
        return results;
    }

    private static GameResult getOpposite(GameResult gameResult) {
        if (gameResult.equals(WIN) || gameResult.equals(BLACKJACK_WIN)) {
            return LOSE;
        }

        if (gameResult.equals(LOSE)) {
            return WIN;
        }

        return DRAW;
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}
