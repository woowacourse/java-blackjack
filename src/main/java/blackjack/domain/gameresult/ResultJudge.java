package blackjack.domain.gameresult;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class ResultJudge {

    private ResultJudge() {
    }

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
        if (isDraw(player, dealer)) {
            return Result.DRAW;
        }
        throw new IllegalArgumentException("승패가 정해지지 않았습니다.");
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

    private static boolean isDraw(Player player, Dealer dealer) {
        return (dealer.isNotBust() && player.isNotBust())
                && player.hasSameScore(dealer);
    }
}
