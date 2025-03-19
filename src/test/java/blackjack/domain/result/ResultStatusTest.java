package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
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
            "WIN, 10_000.0",
            "LOSE, -10_000.0",
            "BLACKJACK, 15_000.0",
            "PUSH, 0.0",
    })
    @DisplayName("수익을 반환한다")
    void calculateProfit(final ResultStatus resultStatus, final BigDecimal expected) {
        // Given
        final BigDecimal bettingAmount = BigDecimal.valueOf(10_000);

        // When & Then
        assertThat(resultStatus.calculateProfit(bettingAmount)).isEqualTo(expected);
    }
}
