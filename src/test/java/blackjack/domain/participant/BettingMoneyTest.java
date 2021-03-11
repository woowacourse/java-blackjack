package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    @DisplayName("값 객체 비교 - 성공")
    void equalsSuccess() {
        int value = 10000;
        BettingMoney money1 = new BettingMoney(value);
        BettingMoney money2 = new BettingMoney(value);

        assertThat(money1).isEqualTo(money2);
        assertThat(money1).hasSameHashCodeAs(money2);
    }

    @Test
    @DisplayName("값 객체 비교 - 실패")
    void equalsFail() {
        BettingMoney money1 = new BettingMoney(10000);
        BettingMoney money2 = new BettingMoney(20000);

        assertThat(money1).isNotEqualTo(money2);
        assertThat(money1.hashCode()).isNotEqualTo(money2.hashCode());
    }
}