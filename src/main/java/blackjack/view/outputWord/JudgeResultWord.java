package blackjack.view.outputWord;

import blackjack.domain.result.JudgeResult;
import blackjack.dto.ResultCount;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum JudgeResultWord {

    WIN(JudgeResult.WIN, "승"),
    PUSH(JudgeResult.PUSH, "무"),
    LOSE(JudgeResult.LOSE, "패");

    private final JudgeResult result;
    private final String word;

    JudgeResultWord(final JudgeResult result, final String word) {
        this.result = result;
        this.word = word;
    }

    public static String toWord(final JudgeResult findWinResult) {
        final JudgeResultWord resultWord = Arrays.stream(values())
                .filter(winResult -> winResult.result == findWinResult)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 승리 결과가 존재하지 않습니다."));
        return resultWord.word;
    }

    public static String toWordsWithCount(final List<ResultCount> dealerJudgeResultStatistic) {
        return dealerJudgeResultStatistic.stream()
                .map(resultCount -> toWordWithCount(resultCount.getJudgeResult(), resultCount.getCount()))
                .collect(Collectors.joining(" "));
    }

    private static String toWordWithCount(final JudgeResult judgeResult, final int count) {
        if (count == 0) {
            return "";
        }
        return count + toWord(judgeResult);
    }
}
