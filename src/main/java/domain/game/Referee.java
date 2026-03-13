package domain.game;

public class Referee {
    private static final int BUST_THRESHOLD = 21;

    public Result judge(int playerScore, int dealerScore) {
        if (playerScore > BUST_THRESHOLD) {
            return Result.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return Result.WIN;
        }
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore == dealerScore) {
            return Result.TIE;
        }
        return Result.LOSE;
    }
}
