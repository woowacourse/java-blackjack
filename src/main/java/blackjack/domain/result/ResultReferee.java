package blackjack.domain.result;

import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;
import blackjack.domain.gameplayer.Score;

public class ResultReferee {

    public static Result getPlayerResult(Player player, Dealer dealer) {
        if (player.isBurst()) {
            return Result.LOSE;
        }
        if (player.isBlackJack()) {
            return getPlayerResultWhenPlayerBlackJack(dealer);
        }
        if (dealer.isBurst()) {
            return Result.WIN;
        }
        return getPlayerResultWhenParticipantsIsNotBurst(player, dealer);
    }

    private static Result getPlayerResultWhenParticipantsIsNotBurst(Player player, Dealer dealer) {
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

    private static Result getPlayerResultWhenPlayerBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return Result.DRAW;
        }
        return Result.BLACKJACK;
    }
}
