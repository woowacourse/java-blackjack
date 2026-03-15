package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

public record BlackjackJudge() {

    public Result judgePlayerResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.BLACKJACK_WIN;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Result.DRAW;
        }
        if (player.isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        if (player.calculateScore() > dealer.calculateScore()) {
            return Result.BLACKJACK_WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public int calculatePlayerProfit(Player player, Result result) {
        return result.getProfit(player.getBetAmount());
    }
}
