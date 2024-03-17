package domain.game;

public enum Result {
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0),
    BLACKJACK("블랙잭", 1.5);


    private final String result;
    private final double profitRate;

    Result(String result, double profitRate) {
        this.result = result;
        this.profitRate = profitRate;
    }

    public Result reverse() {
        return switch (this) {
            case BLACKJACK -> LOSE;
            case WIN -> LOSE;
            case LOSE -> WIN;
            case DRAW -> DRAW;
        };
    }

    public String getResult() {
        return result;
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

    public static Result blackjackCompare(boolean current, boolean opponent) {
        if (current && opponent) {
            return DRAW;
        }
        if (current) {
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
