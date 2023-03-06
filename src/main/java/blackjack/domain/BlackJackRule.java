package blackjack.domain;

public class BlackJackRule {

    private static final int BUST_POINT = 21;
    private final int dealerScore;

    public BlackJackRule(final int dealerScore) {
        this.dealerScore = dealerScore;
    }

    ResultType calculateDealerResult(final int playerScore) {
        if (isTie(dealerScore, playerScore)) {
            return ResultType.TIE;
        }
        if (isDealerWin(dealerScore, playerScore)) {
            return ResultType.WIN;
        }
        return ResultType.LOSE;
    }

    private boolean isTie(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_POINT && dealerScore > BUST_POINT) {
            return true;
        }
        return playerScore == dealerScore;
    }

    private boolean isDealerWin(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_POINT) {
            return true;
        }
        if (dealerScore > BUST_POINT) {
            return false;
        }
        return dealerScore > playerScore;
    }
}
