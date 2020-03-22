package blackjack.domain.result;

import blackjack.domain.user.component.Point;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum ResultType {
    BLACK_JACK("블랙잭", 1.5, (playerPoint, dealerPoint) -> {
        return playerPoint.isBalckJack() && !dealerPoint.isBalckJack();
    }),
    WIN("승", 1, (playerPoint, dealerPoint)
            -> {
        if (playerPoint.isBalckJack() || playerPoint.isBust()) {
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
    DRAW("무", 0, (playerPoint, dealerPoint)
            -> {
        if ((playerPoint.isBalckJack() && !dealerPoint.isBalckJack())
                || (!playerPoint.isBalckJack() && dealerPoint.isBalckJack())) {
            return false;
        }
        if (playerPoint.isNotBust()
                && dealerPoint.isNotBust()
                && playerPoint.isSameWith(dealerPoint)) {
            return true;
        }
        return false;
    }),
    LOSE("패", -1, (playerPoint, dealerPoint)
            -> {
        if (playerPoint.isBust()) {
            return true;
        }
        if (dealerPoint.isBust()) {
            return false;
        }
        if (!playerPoint.isBalckJack() && dealerPoint.isBalckJack()) {
            return true;
        }
        if (playerPoint.diffWithBlackJack() > dealerPoint.diffWithBlackJack()) {
            return true;
        }
        return false;
    });

    private final String message;
    private final double profitRate;
    private final BiFunction<Point, Point, Boolean> judge;

    ResultType(String message, double profitRate, BiFunction<Point, Point, Boolean> judge) {
        this.message = message;
        this.profitRate = profitRate;
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

    public double getProfitRate() {
        return profitRate;
    }
}
