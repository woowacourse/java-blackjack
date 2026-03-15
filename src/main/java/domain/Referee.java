package domain;

public class Referee {
    private static final int BUST_THRESHOLD = 21;

    public Result judge(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (player.getBlackJack()) {
            return Result.BLACKJACK;
        }
        if (playerScore > BUST_THRESHOLD) {
            return Result.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return Result.WIN;
        }
        return judgeScore(playerScore, dealerScore);
    }

    private Result judgeScore(int playerScore, int dealerScore) {
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
