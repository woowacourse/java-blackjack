package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultTest {

    @ParameterizedTest
    @DisplayName("결과 reverse 테스트")
    @CsvSource({"WIN,LOSS", "LOSS,WIN", "DRAW,DRAW"})
    void reverse(Result result, Result expect) {
        assertThat(result.reverse()).isEqualTo(expect);
    }

}
