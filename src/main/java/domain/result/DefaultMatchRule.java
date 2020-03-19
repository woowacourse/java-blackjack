package domain.result;

import domain.user.Dealer;
import domain.user.Player;

public class DefaultMatchRule implements MatchService {
    @Override
    public Result match(Player player, Dealer dealer) {
        if (player.isBust()) {
            return Result.DEALER_WIN;
        }

        if (dealer.isBust() && player.isBlackjack()) {
            return Result.PLAYER_WIN_WITH_BLACKJACK;
        }

        if (dealer.isBust() && player.isNotBlackjack()) {
            return Result.PLAYER_WIN_WITHOUT_BLACKJACk;
        }

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Result.DRAW;
        }

        if (player.isBlackjack() && dealer.isNotBlackjack()) {
            return Result.PLAYER_WIN_WITH_BLACKJACK;
        }

        return matchScore(player, dealer);
    }

    private Result matchScore(Player player, Dealer dealer) {
        int scoreOfPlayer = player.calculateScore();
        int scoreOfDealer = dealer.calculateScore();
        if (scoreOfPlayer < scoreOfDealer) {
            return Result.DEALER_WIN;
        }

        if (scoreOfPlayer == scoreOfDealer) {
            return Result.DRAW;
        }
        return Result.PLAYER_WIN_WITHOUT_BLACKJACk;
    }
}
