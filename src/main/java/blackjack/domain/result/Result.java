package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public class Result {

    private Result() {
    }
    public static BlackjackResult getResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return BlackjackResult.BUST;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return BlackjackResult.TIE;
        }
        if (player.isBlackjack()) {
            return BlackjackResult.BLACKJACK;
        }
        return compareScore(player, dealer);
    }

    private static BlackjackResult compareScore(User targetUser, User compareUser) {
        int compareResult = targetUser.compare(compareUser);
        if (compareResult == 0) {
            return BlackjackResult.TIE;
        }
        if (compareResult > 0) {
            return BlackjackResult.WIN;
        }
        return BlackjackResult.LOSE;
    }
}
