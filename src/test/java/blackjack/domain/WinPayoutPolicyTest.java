package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.bet.PayoutPolicy;
import blackjack.domain.bet.WinPayoutPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinPayoutPolicyTest {

    @Test
    @DisplayName("베팅 금액의 2배로 계산한다.")
    void 베팅_금액의_2배로_계산한다() {
        int amount = 10000;
        PayoutPolicy winPayoutPolicy = new WinPayoutPolicy();
        int expected = 20000;

        int actual = winPayoutPolicy.payout(amount);

        assertThat(actual).isEqualTo(expected);
    }
}
