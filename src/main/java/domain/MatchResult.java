package domain;

public enum MatchResult {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private static final int BLACKJACK_MAX_LIMIT = 21;

    private final double rate;

    MatchResult(double rate) {
        this.rate = rate;
    }

    public static MatchResult of(int gamblerScore, int dealerScore) {
        if(gamblerScore == BLACKJACK_MAX_LIMIT)
            return BLACKJACK;

        if (gamblerScore > BLACKJACK_MAX_LIMIT)
            return LOSE;

        if (dealerScore > BLACKJACK_MAX_LIMIT || gamblerScore > dealerScore)
            return WIN;

        if (gamblerScore < dealerScore)
            return LOSE;

        return DRAW;
    }

    public double getRate(){
        return rate;
    }
}
