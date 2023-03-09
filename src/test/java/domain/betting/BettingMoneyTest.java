package domain.betting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Constants;

public class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -100, 0})
    @DisplayName("배팅 머니가 0 이하의 금액이면 예외 처리한다.")
    void bettingMoneyLessOrEqualZero(int money) {
        Assertions.assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.ERROR_PREFIX + "배팅 금액은 0보다 커야합니다.");
    }
}
