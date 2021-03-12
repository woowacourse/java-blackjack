package blackjack.domain;

public class Player extends Participant {

    private static final int MAXIMUM_SCORE_LIMIT = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int minimumScore = calculateScore();
        return minimumScore < MAXIMUM_SCORE_LIMIT;
    }

    public BetAmount calculateFinalBetProfit(Dealer dealer) {
        Result result = judgeResult(dealer);
        if (result == Result.LOSE) {
            return getBetAmount().toNegative();
        }
        if (result == Result.WIN) {
            return getBetAmount();
        }
        if (result == Result.BLACKJACK) {
            return getBetAmount().toHalf();
        }
        return BetAmount.ZERO;
    }

    public Result judgeResult(Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        int playerScore = calculateScore();
        if (isDealerWin(dealerScore, playerScore)) {
            return Result.LOSE;
        }
        if (isDraw(dealerScore, playerScore, dealer)) {
            return Result.DRAW;
        }
        if (isBlackjack()) {
            return Result.BLACKJACK;
        }
        return Result.WIN;
    }

    private boolean isDealerWin(int dealerScore, int playerScore) {
        return playerScore > MAXIMUM_SCORE_LIMIT
                || (playerScore < dealerScore && dealerScore <= MAXIMUM_SCORE_LIMIT);
    }

    private boolean isDraw(int dealerScore, int playerScore, Dealer dealer) {
        return (dealerScore < MAXIMUM_SCORE_LIMIT && playerScore == dealerScore)
                || (this.isBlackjack() && dealer.isBlackjack());
    }
}
