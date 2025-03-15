package bet;

import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @Test
    void 금액을_입력하여_베팅한다() {
        final int amount = 10000;

        Assertions.assertThatNoException()
                .isThrownBy(() -> new Bet(amount));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, 100_001_000})
    void 베팅_금액이_0_또는_음수이거나_1억원보다_큰_경우_예외가_발생한다(int value) {
        Assertions.assertThatThrownBy(() -> new Bet(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}