package domain;

import java.util.Arrays;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/11
 */
public enum BlackJackWinningResult {
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final int value;

    BlackJackWinningResult(final int value) {
        this.value = value;
    }

    public static BlackJackWinningResult from(int value) {
        return Arrays.stream(values())
                .filter(winningResult -> winningResult.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과값을 확인해주세요."));
    }
}
