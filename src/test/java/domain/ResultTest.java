package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultTest {

    @ParameterizedTest(name = "뒤집을 수 있다")
    @CsvSource({"DRAW,DRAW", "WIN,LOSE", "LOSE,WIN"})
    void test_reverse(Result result, Result reversed) {
        assertThat(result.reverse()).isEqualTo(reversed);
    }
}
