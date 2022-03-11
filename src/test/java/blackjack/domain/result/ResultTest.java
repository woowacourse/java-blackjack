package blackjack.domain.result;

import static blackjack.domain.result.Result.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTest {

    @Test
    @DisplayName("결과의 값이 정확한지 확인")
    void equalsResultName() {
        assertThat(WIN.getName()).isEqualTo("승");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "WIN,패",
        "LOSE,승",
        "DRAW,무"
    })
    @DisplayName("결과가 정상적으로 반대로 뒤집어 지는지 확인")
    void reversResult(Result result, String excepted) {
        Result reverseResult = result.reverse();
        assertThat(reverseResult.getName()).isEqualTo(excepted);
    }
}
