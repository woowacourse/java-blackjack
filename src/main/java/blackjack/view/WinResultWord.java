package blackjack.view;

import blackjack.domain.WinResult;
import java.util.Arrays;

public enum WinResultWord {

    WIN(WinResult.WIN, "승"),
    PUSH(WinResult.PUSH, "무"),
    LOSE(WinResult.LOSE, "패");

    private final WinResult result;
    private final String word;

    WinResultWord(WinResult result, String word) {
        this.result = result;
        this.word = word;
    }

    public static String toWord(WinResult findWinResult) {
        WinResultWord resultWord = Arrays.stream(values())
                .filter(winResult -> winResult.result == findWinResult)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 승리 결과가 존재하지 않습니다."));
        return resultWord.word;
    }
}
