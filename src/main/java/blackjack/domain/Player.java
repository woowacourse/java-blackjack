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

    public Result judgeResult(Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        int playerScore = calculateScore();
        if (isDealerWin(dealerScore, playerScore)) {
            return Result.LOSE;
        }
        if (dealerScore <= MAXIMUM_SCORE_LIMIT && playerScore == dealerScore) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    private boolean isDealerWin(int dealerScore, int playerScore) {
        return playerScore > MAXIMUM_SCORE_LIMIT
            || (playerScore < dealerScore && dealerScore <= MAXIMUM_SCORE_LIMIT);
    }
}
