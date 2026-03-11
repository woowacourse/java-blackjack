package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawPayoutPolicyTest {

    @Test
    @DisplayName("베팅 금액의 2배로 계산한다.")
    void 베팅_금액의_1배로_계산한다() {
        int amount = 10000;
        PayoutPolicy drawPayoutPolicy = new DrawPayoutPolicy();
        int expected = 10000;

        int actual = drawPayoutPolicy.payout(amount);

        assertThat(actual).isEqualTo(expected);
    }
}
