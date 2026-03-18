package domain;

import domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {

    @Test
    @DisplayName("돈은 음수가 될 수 없다.")
    void 돈이_음수인_경우_에러() {
        // given
        int negativeMoney = -3000;

        // when - then
        assertThatThrownBy(() ->  new Money(negativeMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("돈은 0이 될 수 있어야 한다.")
    void 돈이_0인_경우_성공() {
        // given
        int zeroMoney = 0;

        // when
        Money money = new Money(zeroMoney);

        // then
        assertEquals(zeroMoney, money.getValue());
    }
}
