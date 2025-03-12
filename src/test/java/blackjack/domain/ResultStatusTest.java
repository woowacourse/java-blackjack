package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultStatusTest {

    @DisplayName("두 개의 값으로 플레이어의 승리 결과를 계산한다.")
    @CsvSource({
            "20, 19, WIN",
            "20, 20, DRAW",
            "20, 21, LOSE",
            "20, 25, WIN",
            "23, 25, LOSE",
            "23, 18, LOSE"
    })
    @ParameterizedTest
    void calculateResultStatus(final int score, final int comparedScore, final ResultStatus resultStatus) {
        // given

        // when & then
        assertThat(ResultStatus.calculateResultStatus(score, comparedScore)).isEqualTo(resultStatus);
    }
}
