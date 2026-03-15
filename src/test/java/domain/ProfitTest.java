package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProfitTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "100", "-100", "100000000000000"})
    void 수익_금액_생성_성공(String value) {
        assertThatCode(() -> new Profit(new BigDecimal(value)))
                .doesNotThrowAnyException();
    }

    @Test
    void null_입력_시_예외가_발생한다() {
        assertThatThrownBy(() -> new Profit(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"100000000000001", "-100000000000001"})
    void 상한선_초과_시_예외가_발생한다(String value) {
        assertThatThrownBy(() -> new Profit(new BigDecimal(value)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 수익을_반전시킨다() {
        Profit profit = new Profit(new BigDecimal("1500.50"));
        Profit negated = profit.negate();

        assertThat(negated.value()).isEqualByComparingTo("-1500.5");
    }
}
