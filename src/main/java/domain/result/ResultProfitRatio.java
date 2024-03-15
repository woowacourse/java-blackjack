package domain.result;

import java.util.Arrays;

public enum ResultProfitRatio {
    BLACKJACK(1.5),
    WIN(1),
    PUSH(0),
    LOSE(-1);

    private final double ratio;

    ResultProfitRatio(double ratio) {
        this.ratio = ratio;
    }

    public static ResultProfitRatio match(double ratio) {
        return Arrays.stream(ResultProfitRatio.values())
                     .filter(profitRatio -> profitRatio.ratio == ratio)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("해당 비율에 적합한 게임 결과가 존재하지 않습니다."));
    }

    public double getRatio() {
        return ratio;
    }
}
