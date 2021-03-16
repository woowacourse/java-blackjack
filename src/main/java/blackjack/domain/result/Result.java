package blackjack.domain.result;

import blackjack.domain.user.User;

public class Result {

    private Result() {
    }

    public static double compareScoreResult(User targetUser, User compareUser) {
        int compareResult = targetUser.compare(compareUser);
        if (compareResult == 0) {
            return BlackjackResult.TIE.getEarningRate();
        }
        if (compareResult > 0) {
            return BlackjackResult.WIN.getEarningRate();
        }
        return BlackjackResult.LOSE.getEarningRate();
    }
}
