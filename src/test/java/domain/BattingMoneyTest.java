package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BattingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 200000, 1000000})
    public void 정상_범위의_배팅금액_은_생성된다(int money){
        // when & then
        Assertions.assertDoesNotThrow(() -> new BattingMoney(money));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 2000000000})
    public void 범위를_벗어난_금액은_예외가_발생한다(int money) {
        // when & then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BattingMoney(money));
    }
}
