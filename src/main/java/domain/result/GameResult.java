package domain.result;

public enum GameResult {

    WIN("승", 1.0),
    LOSE("패", -1),
    DRAW("무", 0),
    BLACKJACK("승", 1.5);

    public static final int BLACKJACK_SCORE = 21;

    private final String description;
    private final double profit;

    GameResult(String description, double profit) {
        this.description = description;
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public double getProfit() {
        return profit;
    }


}
