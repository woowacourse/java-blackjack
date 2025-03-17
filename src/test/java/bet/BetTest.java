package bet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, 100_001_000})
    void 초기_베팅_금액이_0_또는_음수이거나_1억원보다_큰_경우_예외가_발생한다(int value) {
        Assertions.assertThatThrownBy(() -> new Bet(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 초기_베팅_금액을_추가한다() {
        final int amount = 10000;

        Assertions.assertThatNoException()
                .isThrownBy(() -> new Bet(amount));
    }
}
