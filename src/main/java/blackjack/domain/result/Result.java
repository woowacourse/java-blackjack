package blackjack.domain.result;

import java.util.List;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int BUST = 21;
    private final String message;

    Result(String message) {
        this.message = message;
    }

    public static Result getResult(int playerPoint, int dealerPoint) {
        if (playerPoint > BUST) {
            return LOSE;
        }
        if (dealerPoint > BUST) {
            return WIN;
        }
        if (playerPoint > dealerPoint) {
            return WIN;
        }
        if (playerPoint == dealerPoint) {
            return DRAW;
        }
        return LOSE;
    }

    public static int getSum(Result result, List<Result> results) {
        return (int) results.stream()
                .filter(element -> element == result)
                .count();
    }

    public static Result reverse(Result result) {
        if (result == Result.WIN) {
            return Result.LOSE;
        }
        if (result == Result.LOSE) {
            return Result.WIN;
        }
        return result;
    }

    public String getMessage() {
        return message;
    }
}
