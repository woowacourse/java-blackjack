package blackjack.domain.game;

import blackjack.domain.participant.Dealer2;
import blackjack.domain.participant.Player2;

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

    public static Result determineResult(Player2 player, Dealer2 dealer) {
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

    private static Result compareScore(Player2 player, Dealer2 dealer) {
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
