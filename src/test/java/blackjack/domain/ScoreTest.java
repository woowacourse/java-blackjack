package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ScoreTest {
    @DisplayName("Score 생성자 유효성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 31})
    void scoreConstructorTest(int input) {
        assertThatThrownBy(() -> {
            Score.validate(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
