package blackjack.domain.result;

import blackjack.domain.card.Cards;

import java.util.Arrays;

public enum ResultType {
    BLACK_JACK( 1.5),
    WIN(1),
    DRAW(0),
    LOSE( -1);

    private final double profitRate;

    ResultType(double profitRate) {
        this.profitRate = profitRate;
    }

    public static ResultType computeResult(Cards playerCards, Cards dealerCards) {
        return Arrays.stream(Judge.values())
                .filter(x -> x.judgeState.test(playerCards))
                .map(x -> x.judgeResultType.apply(playerCards, dealerCards))
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

    public double getProfitRate() {
        return profitRate;
    }
}
