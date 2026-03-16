package domain.result;

public enum GameResult {
    BLACKJACK("블랙잭", 3, 2),
    WIN("승", 1, 1),
    DRAW("무", 0, 1),
    LOSE("패", -1, 1);

    private final String description;
    private final int numerator;
    private final int denominator;

    GameResult(final String description, final int numerator, final int denominator) {
        this.description = description;
        this.numerator = numerator;
        this.denominator = denominator;
    }


    public int calculateBetProfit(final int betAmount) {
        return betAmount * numerator / denominator;
    }
}
