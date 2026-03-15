package domain;

public class Referee {
    private static final int BUST_THRESHOLD = 21;

    public boolean isBlackJack(int playerScore) {
        return playerScore == BUST_THRESHOLD;
    }

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

    public double calculateProfit(Result result, int betAmount) {
        return result.getProfitRate() * betAmount;
    }

}
