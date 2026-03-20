package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

public final class BlackjackJudge {

    public Result judgePlayerResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Result.DRAW;
        }
        if (player.isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return Result.LOSE;
        }

        return judgeScore(dealer, player);
    }

    private Result judgeScore(Dealer dealer, Player player) {
        if (player.calculateScore() > dealer.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }
}
