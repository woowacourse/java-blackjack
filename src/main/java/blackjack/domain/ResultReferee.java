package blackjack.domain;

import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;

public class ResultReferee {

    public static Result getPlayerResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (player.isBlackJack()) {
            return getResultWhenPlayerBlackJack(dealer);
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        return getPlayerResultWhenParticipantsIsNotBust(player, dealer);
    }

    private static Result getPlayerResultWhenParticipantsIsNotBust(Player player, Dealer dealer) {
        Score playerScore = player.calculateScore();
        Score dealerScore = dealer.calculateScore();
        if (playerScore.isGreaterThan(dealerScore)) {
            return Result.WIN;
        }
        if (playerScore.isEqualTo(dealerScore)) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private static Result getResultWhenPlayerBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return Result.DRAW;
        }
        return Result.BLACKJACK;
    }
}
