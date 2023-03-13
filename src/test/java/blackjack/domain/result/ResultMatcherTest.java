package blackjack.domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultMatcherTest {

    @ParameterizedTest
    @CsvSource(value = {"19:18:WIN", "18:19:LOSE", "18:18:TIE", "23:18:LOSE", "23:22:TIE", "14:22:WIN"}, delimiter = ':')
    @DisplayName("calculateResult()는 딜러와 플레이어의 점수를 받아 플레이어의 결과를 반환한다.")
    void test_calculateResult(String playerScore, String dealerScore, String inputResult) {
        // given & when
        ResultMatcher resultMatcher = ResultMatcher.calculateResult(Integer.parseInt(playerScore), Integer.parseInt(dealerScore));
        ResultMatcher expectedResultMatcher = ResultMatcher.valueOf(inputResult);

        // then
        Assertions.assertThat(resultMatcher).isEqualTo(expectedResultMatcher);
    }
}
