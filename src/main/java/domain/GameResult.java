package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public class GameResult {
    public static Result judgeResult(Player player, Dealer dealer) {
        if (player.isBlackjack() || dealer.isBlackjack()) {
            return judgeBlackjack(player, dealer);
        }
        if (player.isBust() || dealer.isBust()) {
            return judgeBust(player);
        }
        return judgeByScore(player, dealer);
    }

    private static Result judgeBlackjack(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Result.DRAW;
        }
        if (player.isBlackjack()) {
            return Result.BLACKJACK;
        }
        return Result.LOSE;
    }

    private static Result judgeBust(Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }


    private static Result judgeByScore(Player player, Dealer dealer) {
        Score score = player.getTotalSum();
        Score targetScore = dealer.getTotalSum();

        if (score.isEqualTo(targetScore)) {
            return Result.DRAW;
        }
        if (score.isGreaterThan(targetScore)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }
}
