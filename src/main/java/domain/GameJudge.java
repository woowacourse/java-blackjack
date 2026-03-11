package domain;

import vo.GameResult;

public class GameJudge {
    private static final Integer BUST_THRESHOLD = 21;

    public GameResult judge(int dealerScore, int userScore, boolean userBlackjack) {
        if (userScore > BUST_THRESHOLD) {
            return GameResult.BUST;
        }
        if (userScore == dealerScore) {
            return GameResult.PUSH;
        }
        if (userBlackjack) {
            return GameResult.BLACKJACK;
        }
        if (dealerScore > BUST_THRESHOLD || userScore > dealerScore) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }
}
