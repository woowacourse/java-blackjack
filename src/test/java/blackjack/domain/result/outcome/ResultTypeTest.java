package blackjack.domain.result.outcome;

import blackjack.domain.result.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTypeTest {
    @DisplayName("승/패/무 reverse 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN, LOSE", "DRAW, DRAW", "LOSE, WIN"})
    void test2(ResultType resultType, ResultType expectedType) {
        ResultType actualType = resultType.reverse();
        assertThat(actualType).isEqualTo(expectedType);
    }
}
