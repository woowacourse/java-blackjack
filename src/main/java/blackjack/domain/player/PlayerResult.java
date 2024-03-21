package blackjack.domain.player;

import blackjack.domain.hand.Score;
import blackjack.domain.dealer.Dealer;

public enum PlayerResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    TIE(0),
    ;

    private final double multiplier;

    PlayerResult(double multiplier) {
        this.multiplier = multiplier;
    }

    public static double determineMultiplier(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE.multiplier;
        }
        if (player.isBlackjack() && dealer.isNotBlackjack()) {
            return BLACKJACK_WIN.multiplier;
        }
        if (dealer.isBust()) {
            return WIN.multiplier;
        }
        return compareScore(player, dealer);
    }

    private static double compareScore(Player player, Dealer dealer) {
        Score playerScore = player.handScore();
        Score dealerScore = dealer.handScore();

        if (playerScore.isBiggerThan(dealerScore)) {
            return WIN.multiplier;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return LOSE.multiplier;
        }
        return TIE.multiplier;
    }
}
