package team.blackjack.service;

import java.util.Map;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Player;
import team.blackjack.domain.Result;

public class BlackjackJudge {

    public Result judge(Player player, Dealer dealer) {
        if (player.isBust()) {
            return Result.LOSE;
        }

        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return Result.BLACKJACK;
        }

        if (dealer.isBust()) {
            return Result.WIN;
        }

        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (playerScore < dealerScore) {
            return Result.LOSE;
        }
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public double calculatePlayerRevenue(Player player, Result result) {
        return result.getOdds() * player.getBatMoney();
    }

    public double calculateDealerRevenue(Map<Player, Result> judgeResults) {
        return judgeResults.entrySet().stream()
                .mapToDouble(entry -> calculatePlayerRevenue(entry.getKey(), entry.getValue()))
                .sum() * -1;
    }
}
