package view;

import java.util.Arrays;

public enum Result {
    BUST("버스트", 0), BLACKJACK("블랙잭", -1);

    private final String result;
    private final int handValue;

    Result(String result, int handValue) {
        this.result = result;
        this.handValue = handValue;
    }

    public static String getResultOf(int handValue) {
        return Arrays.stream(Result.values())
            .filter(item -> item.handValue == handValue)
            .map(item -> item.result)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("알 수 없는 handValue입니다."));
    }
}
