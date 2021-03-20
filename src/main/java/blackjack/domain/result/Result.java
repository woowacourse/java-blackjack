package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public class Result {

    private Result() {
    }

    public static double compareScoreResult(Player player, Dealer dealer) {
        int compareResult = player.compare(dealer);
        if (!player.isBust() && compareResult == 0) {
            return BlackjackResult.TIE.getEarningRate();
        }
        if (dealer.isBust() || compareResult > 0) {
            return BlackjackResult.WIN.getEarningRate();
        }
        return BlackjackResult.LOSE.getEarningRate();
    }
}
