package blackjack.domain.game;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    BLACKJACK("블랙잭");

    private static final double BLACKJACK_BONUS = 1.5;

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }


    public int calculateProfit(final int amount) {
        if (this == BLACKJACK) {
            return (int) (amount * BLACKJACK_BONUS);
        }
        if (this == WIN) {
            return amount;
        }
        if (this == DRAW) {
            return 0;
        }
        return -amount;
    }
}
