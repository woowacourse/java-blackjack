package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(longs = {1000, 200000, 1000000, Long.MAX_VALUE})
    public void 정상_범위의_배팅금액_은_생성된다(long money) {
        // when & then
        Assertions.assertDoesNotThrow(() -> new BettingMoney(money));
    }

    @ParameterizedTest
    @ValueSource(longs = {-10, -1, Long.MIN_VALUE})
    public void 범위를_벗어난_금액은_예외가_발생한다(long money) {
        // when & then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BettingMoney(money));
    }
}
