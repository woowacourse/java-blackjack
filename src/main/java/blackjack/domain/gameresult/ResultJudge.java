package blackjack.domain.gameresult;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class ResultJudge {

    public static Result makeResult(Player player, Dealer dealer) {
        if (isBlackJackWin(player, dealer)) {
            return Result.BLACKJACK_WIN;
        }
        if (isWin(player, dealer)) {
            return Result.WIN;
        }
        if (isLose(player, dealer)) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    private static boolean isBlackJackWin(Player player, Dealer dealer) {
        return player.isFirstTurnBackJack() && dealer.isNotBlackJack();
    }

    private static boolean isWin(Player player, Dealer dealer) {
        return (dealer.isBust() && player.isNotBust())
                || player.isNotBust() && player.hasHigherScore(dealer);
    }

    private static boolean isLose(Player player, Dealer dealer) {
        return player.isBust()
                || player.isNotBust() && player.hasLowerScore(dealer);
    }

    private boolean isDraw(Player player, Dealer dealer) {
        return (dealer.isNotBust() && player.isNotBust())
                && player.hasSameScore(dealer);
    }
}
