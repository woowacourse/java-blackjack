package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Result {

    WIN,
    LOSE,
    TIE,
    ;

    Result() {
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLoss() {
        return this == LOSE;
    }

    public boolean isTie() {
        return this == TIE;
    }

    public static Result determineResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (player.isBlackjack() && dealer.isNotBlackjack()) {
            return Result.WIN;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        return compareScore(player, dealer);
    }

    private static Result compareScore(Player player, Dealer dealer) {
        Score playerScore = player.handScore();
        Score dealerScore = dealer.handScore();

        if (playerScore.isBiggerThan(dealerScore)) {
            return Result.WIN;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return Result.LOSE;
        }
        return Result.TIE;
    }
}
