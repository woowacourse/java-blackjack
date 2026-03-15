package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingTest {
    @ParameterizedTest
    @ValueSource(ints = {999, 300_000_001})
    void 베팅한도_초과_입력_예외_발생(int amount) {
        assertThatThrownBy(() -> new Betting(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 300_000_000, 299_999_999})
    void 정상_배팅_금액_정상_동작_확인(int amount) {
        assertThatCode(() -> new Betting(amount))
                .doesNotThrowAnyException();
    }
}
