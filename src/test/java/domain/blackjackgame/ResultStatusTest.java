package domain.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultStatusTest {
    @ParameterizedTest
    @CsvSource(value = {"WIN:LOSE", "DRAW:DRAW", "LOSE:WIN"}, delimiter = ':')
    void 게임_결과_상태를_반대로_뒤집는다(ResultStatus status, ResultStatus expected) {
        ResultStatus actual = status.reverse();

        assertThat(actual).isEqualTo(expected);
    }
}
