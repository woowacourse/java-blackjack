package domain;

import domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("베팅금액이 1.5배 되어야 한다.")
    void 베팅금액을_1점5배로_계산() {
        // given
        Money money = new Money(3000);

        // when
        Integer appliedBlackjack = money.applyBlackjack();

        // then
        Assertions.assertEquals((int) (3000*1.5), appliedBlackjack);
    }
}
