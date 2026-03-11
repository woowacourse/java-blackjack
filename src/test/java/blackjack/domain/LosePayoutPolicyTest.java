package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LosePayoutPolicyTest {

    @Test
    @DisplayName("베팅 금액의 0배로 계산한다.")
    void 베팅_금액의_0배로_계산한다() {
        int amount = 10000;
        LosePayoutPolicy losePayoutPolicy = new LosePayoutPolicy();
        int expected = 0;

        int actual = losePayoutPolicy.payout(amount);

        assertThat(actual).isEqualTo(expected);
    }
}
