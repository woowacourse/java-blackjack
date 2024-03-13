package blackjack.model.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultCommandTest {

    @ParameterizedTest
    @CsvSource(value = {"WIN,LOSE", "LOSE,WIN", "DRAW,DRAW"})
    @DisplayName("WIN이나 LOSE인 경우 반대의 결과를 반환하고, DRAW인 경우 DRAW를 반환한다.")
    void findOpposite(ResultCommand origin, ResultCommand expected) {
        Assertions.assertThat(origin.findOpposite()).isEqualTo(expected);
    }
}
