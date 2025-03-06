package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultStatusTest {

    @DisplayName("두 개의 값으로 승리 결과를 계산한다.")
    @CsvSource({
            "19, WIN",
            "20, DRAW",
            "21, LOSE"
    })
    @ParameterizedTest
    void calculateResultStatus(final int comparedSum, final ResultStatus resultStatus) {
        // given
        int sum = 20;

        // when & then
        assertThat(ResultStatus.calculateResultStatus(sum, comparedSum)).isEqualTo(resultStatus);
    }
}