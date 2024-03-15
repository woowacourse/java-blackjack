package blackjack.domain.game;

import blackjack.domain.participant.Dealer2;
import blackjack.domain.participant.Player2;

public enum Result2 {

    WIN,
    LOSE,
    TIE,
    ;

    Result2() {
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

    public static Result2 determineResult(Player2 player, Dealer2 dealer) {
        if (player.isBust()) {
            return Result2.LOSE;
        }
        if (player.isBlackjack() && dealer.isNotBlackjack()) {
            return Result2.WIN;
        }
        if (dealer.isBust()) {
            return Result2.WIN;
        }
        return compareScore(player, dealer);
    }

    private static Result2 compareScore(Player2 player, Dealer2 dealer) {
        Score playerScore = player.handScore();
        Score dealerScore = dealer.handScore();

        if (playerScore.isBiggerThan(dealerScore)) {
            return Result2.WIN;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return Result2.LOSE;
        }
        return Result2.TIE;
    }
}
