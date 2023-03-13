package blackjack.domain.result;

public enum ResultMatcher {
    BLACKJACK(1.5),
    WIN(1.0),
    TIE(0),
    LOSE(-1.0);

    private final double ratio;

    ResultMatcher(double ratio) {
        this.ratio = ratio;
    }

    public static ResultMatcher calculateResult(int playerScore, int dealerScore) {
        if (playerScore > 21) {
            playerScore = 0;
        }
        if (dealerScore > 21) {
            dealerScore = 0;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        return TIE;
    }

    public double getRatio() {
        return ratio;
    }
}
