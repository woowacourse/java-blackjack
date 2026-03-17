package team.blackjack.service;

import team.blackjack.domain.Dealer;
import team.blackjack.domain.Player;
import team.blackjack.domain.Result;

public class BlackjackJudge {

    public Result judge(Player player, Dealer dealer) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore < dealerScore) {
            return Result.LOSE;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return Result.BLACKJACK;
        }
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        return Result.DRAW;
    }
}
