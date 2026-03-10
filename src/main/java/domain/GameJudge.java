package domain;

import vo.GameResult;

public class GameJudge {
    private static final Integer BUST_THRESHOLD = 21;

    public GameResult judge(int dealerScore, int userScore) {
        if (userScore > BUST_THRESHOLD) {
            return GameResult.BUST;
        }
        if (dealerScore > BUST_THRESHOLD || userScore > dealerScore) {
            return GameResult.WIN;
        }
        if (userScore == dealerScore) {
            return GameResult.PUSH;
        }
        return GameResult.LOSE;
    }
}