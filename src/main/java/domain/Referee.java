package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

public class Referee {

    private Referee() {
    }

    public static PlayerResult judgePlayer(final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            return isDealerBust(player);
        }
        if (dealer.isBlackJack()) {
            return isDealerBlackJack(player);
        }
        return isDealerNormalScore(dealer, player);
    }

    private static PlayerResult isDealerBust(final Player player) {
        if (player.isBlackJack()) {
            return PlayerResult.BLACK_JACK_WIN;
        }
        if (!player.isBust()) {
            return PlayerResult.WIN;
        }
        return PlayerResult.LOSE;
    }

    private static PlayerResult isDealerBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return PlayerResult.TIE;
        }
        return PlayerResult.LOSE;
    }

    private static PlayerResult isDealerNormalScore(final Dealer dealer, final Player player) {
        if (player.isBlackJack()) {
            return PlayerResult.BLACK_JACK_WIN;
        }
        if (player.isNormalScore()) {
            return judeByNormalScore(dealer, player);
        }
        return PlayerResult.LOSE;
    }

    private static PlayerResult judeByNormalScore(final Dealer dealer, final Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();
        if (dealerScore < playerScore) {
            return PlayerResult.WIN;
        }
        if (dealerScore > playerScore) {
            return PlayerResult.LOSE;
        }
        return PlayerResult.TIE;
    }
}
