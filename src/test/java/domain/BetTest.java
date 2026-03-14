package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @ParameterizedTest
    @ValueSource(strings = {"10", "10000", "1000000000"})
    void 베팅_금액_생성_성공(String amount) {
        assertThatCode(() -> new Bet(new BigDecimal(amount)))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "9", "-10", "1000000001"})
    void 범위_밖의_금액은_예외가_발생한다(String amount) {
        assertThatThrownBy(() -> new Bet(new BigDecimal(amount)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "105", "1001"})
    void 단위에_맞지_않는_금액은_예외가_발생한다(String amount) {
        assertThatThrownBy(() -> new Bet(new BigDecimal(amount)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void null_입력_시_예외가_발생한다() {
        assertThatThrownBy(() -> new Bet(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
