package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

public class Referee {

    private Referee() {
    }

    public static Result judge(final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            return isDealerBust(player);
        }
        if (dealer.isBlackJack()) {
            return isDealerBlackJack(player);
        }
        return isDealerNormalScore(dealer, player);
    }

    private static Result isDealerBust(final Player player) {
        if (!player.isBust()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    private static Result isDealerBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return Result.TIE;
        }
        return Result.WIN;
    }

    private static Result isDealerNormalScore(final Dealer dealer, final Player player) {
        if (player.isBlackJack()) {
            return Result.LOSE;
        }
        if (player.isNormalScore()) {
            return judeByNormalScore(dealer, player);
        }
        return Result.WIN;
    }

    private static Result judeByNormalScore(final Dealer dealer, final Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();
        if (dealerScore > playerScore) {
            return Result.WIN;
        }
        if (dealerScore < playerScore) {
            return Result.LOSE;
        }
        return Result.TIE;
    }

}
