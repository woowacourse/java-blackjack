package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ScoreTest {
    @ParameterizedTest
    @ValueSource(ints = {Constant.SCORE_MAX_SIZE + 1, Constant.SCORE_MIN_SIZE - 1})
    void 범위를_벗어난_입력은_예외가_발생해야_한다(int value) {
        Assertions.assertThatThrownBy(() -> new Score(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void 정상적인_입력은_올바른_값을_가져야_한다(int value) {
        Assertions.assertThat(new Score(value).getValue()).isEqualTo(value);
    }

}
