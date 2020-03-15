package blackjack.domain.result;

import blackjack.domain.user.Point;

public enum ResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String message;

    ResultType(String message) {
        this.message = message;
    }

    public static ResultType computeResult(Point playerPoint, Point dealerPoint) {
        if (playerPoint.isBust()) {
            return LOSE;
        }
        if (dealerPoint.isBust()) {
            return WIN;
        }

        if (playerPoint.compareTo(dealerPoint) == 1) {
            return WIN;
        }
        if (playerPoint.compareTo(dealerPoint) == 0) {
            return DRAW;
        }
        return LOSE;
    }

    public static ResultType opposite(ResultType resultType) {
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
