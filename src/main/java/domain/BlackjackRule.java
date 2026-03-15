package domain;

import static domain.enums.GameResult.BLACKJACK_WIN;
import static domain.enums.GameResult.DRAW;
import static domain.enums.GameResult.LOSE;
import static domain.enums.GameResult.WIN;

import domain.enums.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackRule {

    public static GameResult judgePlayerResult(Player player, Dealer dealer) {
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

    public static Map<GameResult, Integer> judgeDealerResult(List<GameResult> playerGameResults) {
        Map<GameResult, Integer> results = new HashMap<>();
        playerGameResults.forEach(
                result -> {
                    GameResult dealerGameResult = BlackjackRule.getOpposite(result);
                    results.put(dealerGameResult, results.getOrDefault(dealerGameResult, 0) + 1);
                }
        );
        return results;
    }

    private static GameResult getOpposite(GameResult gameResult) {
        if (gameResult.equals(DRAW)) {
            return DRAW;
        }

        if (gameResult.equals(LOSE)) {
            return WIN;
        }

        return LOSE;
    }
}
