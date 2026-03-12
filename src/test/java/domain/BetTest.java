package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @ParameterizedTest
    @ValueSource(ints = {10_000, 20_000, 1_000_000})
    void 베팅_금액_생성은_양수만_가능하다(int amount) {
        assertThatCode(() -> new Bet(amount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-9999, -10001, 0, -15000})
    void 베팅_금액이_양수가_아니면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
