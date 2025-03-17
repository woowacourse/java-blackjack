package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultStatusTest {

    @DisplayName("정반대 우승 결과를 반환한다")
    @CsvSource({
            "WIN, LOSE",
            "LOSE, WIN",
            "PUSH, PUSH"
    })
    @ParameterizedTest
    void 정반대_우승_결과를_반환한다(final ResultStatus resultStatus, final ResultStatus expected) {
        // Given

        // When & Then
        assertThat(resultStatus.makeOppositeResult()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "WIN, 1.0",
            "LOSE, -1.0",
            "BLACKJACK, -1.5",
            "PUSH, 0",
    })
    @DisplayName("수익률을 반환한다")
    void calculateProfitRate(final ResultStatus resultStatus, final double profitRate) {
        // Given

        // When & Then
        assertThat(resultStatus.getProfitRate()).isEqualTo(profitRate);
    }
}
