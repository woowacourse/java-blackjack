package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("승부 결과 테스트")
class MatchResultTest {

    @DisplayName("각 승부 결과가 가진 배율만큼 곱한다.")
    @ParameterizedTest
    @CsvSource({
            "BLACKJACK, 2.5",
            "WIN, 2.0",
            "DRAW, 1.0",
            "LOSE, 0.0",
    })
    void applyMultiplierTest(MatchResult matchResult, double multiplier) {
        // given
        int betAmount = 1_000;

        // when
        int result = matchResult.applyMultiplier(betAmount);

        // then
        assertThat(result)
                .isEqualTo((int) (betAmount * multiplier));
    }
}
