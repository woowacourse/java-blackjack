package blackjack.domain.gameresult;

import java.util.stream.Stream;

import static blackjack.domain.gameresult.Result.*;

public enum ProfitRate {

    BLACKJACK_WIN_RATE(BLACKJACK_WIN, 1.5),
    WIN_RATE(WIN, 1),
    LOSE_PROFIT(LOSE, -1),
    DRAW_PROFIT(DRAW, 0);

    private final Result result;
    private final double rate;

    ProfitRate(Result result, double rate) {
        this.result = result;
        this.rate = rate;
    }

    public static double calculateProfit(Result targetResult, double batting) {
        ProfitRate profitRate = Stream.of(values())
                .filter(rp -> rp.result == targetResult)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("경기 결과와 매칭되는 수익 반환식이 없습니다."));
        return profitRate.rate * batting;
    }
}
