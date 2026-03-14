package domain;

import domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("돈은 음수가 될 수 없다.")
    void 돈이_음수인_경우_에러() {
        // given
        int negativeMoney = -3000;

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Money(negativeMoney));
    }
}
