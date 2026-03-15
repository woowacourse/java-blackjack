package domain;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Test
    public void 생성된_배팅금액_은_변경되지_않는다() throws Exception {
        // given
        Class<BettingMoney> battingMoneyClass = (Class<BettingMoney>) Class.forName("domain.BettingMoney");
        Field recordMoney = battingMoneyClass.getDeclaredField("money");
        // when & then
        Assertions.assertThrows(IllegalAccessException.class, () -> recordMoney.set("money", 30000));
    }

    @Test
    public void 배팅금액이_같은_경우_동일하게_취급된다() {
        // given
        BettingMoney bettingMoney1 = new BettingMoney(1000);
        BettingMoney bettingMoney2 = new BettingMoney(1000);
        // when & then
        Assertions.assertEquals(bettingMoney1, bettingMoney2);
    }
}
