package blackjack.domain.profit;

import blackjack.domain.participant.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Result {

    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    TIE(0),
    ;

    private final double multiplier;

    Result(double multiplier) {
        this.multiplier = multiplier;
    }

    public static double determineMultiplier(Player player, Dealer dealer) {
        if (player.isBust()) {
            return Result.LOSE.multiplier;
        }
        if (player.isBlackjack() && dealer.isNotBlackjack()) {
            return Result.BLACKJACK_WIN.multiplier;
        }
        if (dealer.isBust()) {
            return Result.WIN.multiplier;
        }
        return compareScore(player, dealer);
    }

    private static double compareScore(Player player, Dealer dealer) {
        Score playerScore = player.handScore();
        Score dealerScore = dealer.handScore();

        if (playerScore.isBiggerThan(dealerScore)) {
            return Result.WIN.multiplier;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return Result.LOSE.multiplier;
        }
        return Result.TIE.multiplier;
    }
}
