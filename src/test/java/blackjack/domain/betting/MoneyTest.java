package blackjack.domain.betting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "100000000"})
    void 돈이_정상적으로_생성된다(final String value) {
        assertThatCode(() -> new Money(value))
                .doesNotThrowAnyException();
    }

    @Test
    void 음수의_돈은_만들어지지_않는다() {
        assertThatThrownBy(() -> new Money("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈의 액수는 0 이상이어야 합니다.");
    }

}
