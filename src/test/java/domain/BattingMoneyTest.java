package domain;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BattingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 200000, 1000000})
    public void 정상_범위의_배팅금액_은_생성된다(int money) {
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

    @Test
    public void 생성된_배팅금액_은_변경되지_않는다() throws Exception {
        // given
        Class<BattingMoney> battingMoneyClass = (Class<BattingMoney>) Class.forName("domain.BattingMoney");
        Field recordMoney = battingMoneyClass.getDeclaredField("money");
        // when & then
        Assertions.assertThrows(IllegalAccessException.class, () -> recordMoney.set("money", 30000));
    }

    @Test
    public void 배팅금액이_같은_경우_동일하게_취급된다() {
        // given
        BattingMoney battingMoney1 = new BattingMoney(1000);
        BattingMoney battingMoney2 = new BattingMoney(1000);
        // when & then
        Assertions.assertEquals(battingMoney1, battingMoney2);
    }
}
