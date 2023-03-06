package blackjack.view;

import blackjack.domain.JudgeResult;
import java.util.Arrays;

public enum JudgeResultWord {

    WIN(JudgeResult.WIN, "승"),
    PUSH(JudgeResult.PUSH, "무"),
    LOSE(JudgeResult.LOSE, "패");

    private final JudgeResult result;
    private final String word;

    JudgeResultWord(JudgeResult result, String word) {
        this.result = result;
        this.word = word;
    }

    public static String toWord(JudgeResult findWinResult) {
        JudgeResultWord resultWord = Arrays.stream(values())
                .filter(winResult -> winResult.result == findWinResult)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 승리 결과가 존재하지 않습니다."));
        return resultWord.word;
    }
}
