package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void 정상적인_입력은_올바른_값을_가져야_한다(int value) {
        Assertions.assertThat(new Score(value).getValue()).isEqualTo(value);
    }

}
