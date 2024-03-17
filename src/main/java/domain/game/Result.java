package domain.game;

public enum Result {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private final double profitRate;

    Result(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public static Result compare(int current, int opponent) {
        if (current > opponent) {
            return WIN;
        }
        if (current < opponent) {
            return LOSE;
        }
        return DRAW;
    }

    public static Result blackjackCompare(boolean isPlayerBlackjack, boolean isDealerBlackjack) {
        if (isPlayerBlackjack && isDealerBlackjack) {
            return DRAW;
        }
        if (isPlayerBlackjack) {
            return BLACKJACK;
        }
        return LOSE;
    }

    public static Result bustCompare(boolean current, boolean opponent) {
        if (current) {
            return LOSE;
        }
        return WIN;
    }
}
