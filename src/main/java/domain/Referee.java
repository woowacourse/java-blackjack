package domain;

public class Referee {
    private static final int BUST_THRESHOLD = 21;

    public Result judge(int playerScore, int dealerScore) {
        if (playerScore > BUST_THRESHOLD) {
            return Result.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return Result.WIN;
        }
        if (playerScore == BUST_THRESHOLD) {
            return Result.BLACKJACK;
        }
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore == dealerScore) {
            return Result.TIE;
        }
        return Result.LOSE;
    }

    public double calculateProfit(Result result, int betAmount) {
        return result.getProfitRate() * betAmount;
    }

}
