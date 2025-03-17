package participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    @DisplayName("최대 금액을 초과해서 배팅할 수 없다.")
    void over_maximum() {
        // given
        final int money = 100001;

        // when
        // then
        Assertions.assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 100,000원을 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("최소 금액보다 작게 배팅할 수 없다.")
    void under_minimum() {
        // given
        final int money = 0;

        // when
        // then
        Assertions.assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 0원을 초과해야 합니다.");
    }
}
