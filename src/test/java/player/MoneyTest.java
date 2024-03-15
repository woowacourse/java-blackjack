package player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, -200})
    @DisplayName("입력한 돈이 음수이거나 양수일 경우 에러를 낸다.")
    void isNegativeValue(int bettingMoney) {
        Assertions.assertThatThrownBy(() -> new Money(bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0원이거나 음수일 수 없습니다.");
    }
}
