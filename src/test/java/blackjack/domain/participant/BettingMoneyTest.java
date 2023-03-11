package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    void 음수_금액이_입력될_수_없습니댜() {
        assertThatThrownBy(() -> new BettingMoney(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0보다 작을 수 없습니다.");
    }

    @Test
    void profit() {
        final BettingMoney bettingMoney = new BettingMoney(1000);
        final int result = bettingMoney.profit(1);
        assertThat(result)
                .isEqualTo(1000);
    }
}
