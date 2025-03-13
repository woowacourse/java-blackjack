package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultStatusTest {

    @DisplayName("두 개의 값으로 딜러의 승리 결과를 계산한다.")
    @CsvSource({
            "20, 19, LOSE",
            "20, 20, PUSH",
            "20, 21, WIN",
            "20, 25, LOSE",
            "23, 25, WIN",
            "23, 18, WIN"
    })
    @ParameterizedTest
    void calculateResultStatus(final int score, final int comparedScore, final ResultStatus resultStatus) {
        // given

        // when & then
        assertThat(ResultStatus.calculateResultStatus(score, comparedScore)).isEqualTo(resultStatus);
    }
}
