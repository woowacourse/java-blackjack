package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BetTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("최소 베팅 금액보다 적다면 예외가 발생한다.")
    void validate_underMinAmount_ExceptionThrown(int less) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Bet(less));
    }
}
