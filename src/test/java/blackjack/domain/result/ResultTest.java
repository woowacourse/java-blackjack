package blackjack.domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultTest {

    @ParameterizedTest
    @CsvSource(value = {
            "true, WIN",
            "false, LOSE"})

    @DisplayName("불린 값으로 결과 이넘 반환 기능 테스트")
    void fromWinState(boolean isWin, Result result) {
        assertThat(Result.fromBoolean(isWin))
                .isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "WIN, LOSE",
            "LOSE, WIN",
            "DRAW, DRAW"})

    @DisplayName("승/패여부 반대로, 무승부->무승부 리턴하는 기능 테스트")
    void toReverse(Result result, Result resultReversed) {
        assertThat(result.toReverse()).isEqualTo(resultReversed);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "WIN, 승",
            "LOSE, 패",
            "DRAW, 무"})

    @DisplayName("결과 글자 리턴 기능 테스트")
    void getResultText(Result result, String text) {
        assertThat(result.getResultText()).isEqualTo(text);
    }
}
