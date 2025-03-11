package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @DisplayName("금액에 대한 객체가 생성되면, 해당 금액을 갖고 있다.")
    @Test
    void test_BettingMoneyCreate() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        int amount = bettingMoney.intValue();

        // then
        assertThat(amount).isEqualTo(10000);
    }

}