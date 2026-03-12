package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BatTest {

    @ParameterizedTest
    @ValueSource(ints = {10_000, 20_000, 1_000_000, 0})
    void 베팅_금액은_만원_단위로_생성_가능하다(int amount) {
        assertThatCode(() -> new Bat(amount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {9999, 10001, 15000, -15000})
    void 베팅_금액이_10000원_단위가_아니면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> new Bat(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 베팅_금액은_음수를_가질_수_있다() {
        assertThatCode(() -> new Bat(-10000))
                .doesNotThrowAnyException();
    }
}
