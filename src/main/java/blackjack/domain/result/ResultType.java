package blackjack.domain.result;

import blackjack.domain.user.Point;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum ResultType {
    WIN("승", (playerPoint, dealerPoint)
            -> {
        if (playerPoint.isBust()) {
            return false;
        }
        if (playerPoint.isNotBust() && dealerPoint.isBust()) {
            return true;
        }
        if (playerPoint.diffWithBlackJack() < dealerPoint.diffWithBlackJack()) {
            return true;
        }
        return false;
    }),
    DRAW("무", (playerPoint, dealerPoint)
            -> {
        if (playerPoint.isNotBust()
                && dealerPoint.isNotBust()
                && playerPoint.compareTo(dealerPoint) == 0) {
            return true;
        }
        return false;
    }),
    LOSE("패", (playerPoint, dealerPoint)
            -> {
        if (playerPoint.isBust()) {
            return true;
        }
        if (dealerPoint.isBust()) {
            return false;
        }
        if (playerPoint.diffWithBlackJack() > dealerPoint.diffWithBlackJack()) {
            return true;
        }
        return false;
    });

    private final String message;
    private final BiFunction<Point, Point, Boolean> judge;

    ResultType(String message, BiFunction<Point, Point, Boolean> judge) {
        this.message = message;
        this.judge = judge;
    }

    public boolean getJudgement(Point playerPoint, Point dealerPoint) {
        return judge.apply(playerPoint, dealerPoint);
    }

    public static ResultType computeResult(Point playerPoint, Point dealerPoint) {
        return Arrays.stream(ResultType.values())
                .filter(x -> x.getJudgement(playerPoint, dealerPoint))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 판단할 수 없습니다."));
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
