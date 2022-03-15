package blackJack.domain.result;

public enum MatchResult {
    BLACK_JACK_WIN("블랙잭", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private static final int BLACK_JACK = 21;

    private final String result;
    private final double profitCoefficient;

    MatchResult(String result, double profitCoefficient) {
        this.result = result;
        this.profitCoefficient = profitCoefficient;
    }

    public static boolean isBurstScore(int score) {
        return score > BLACK_JACK;
    }

    public static boolean isBlackJackScore(int score) {
        return score == BLACK_JACK;
    }

    public int calculateProfit(int bettingAmount) {
        return (int) (bettingAmount * profitCoefficient);
    }

    public String getResult() {
        return result;
    }
}
