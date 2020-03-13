package blackjack.domain.result;

import java.util.List;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    DRAW("무");
    private static final int BUST = 21;
    private final String message;

    ResultType(String message) {
        this.message = message;
    }

    public static ResultType computeResult(int playerPoint, int dealerPoint) {
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

    public static int computeSum(ResultType resultType, List<ResultType> resultTypes) {
        return (int) resultTypes.stream()
                .filter(element -> element == resultType)
                .count();
    }

    public static ResultType reverse(ResultType resultType) {
        if (resultType == ResultType.WIN) {
            return ResultType.LOSE;
        }
        if (resultType == ResultType.LOSE) {
            return ResultType.WIN;
        }
        return resultType;
    }

    public String getMessage() {
        return message;
    }
}
