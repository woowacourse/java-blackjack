package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("음수로 배팅금액을 생성할 시 예외가 발생한다.")
    @Test
    void test_BettingMoneyCreate_negative() {
        // given
        int betValue = -1;
        // when & then
        assertThatThrownBy(() -> new BettingMoney(betValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[Error] 배팅 금액은 음수가 될 수 없습니다.");
    }

}