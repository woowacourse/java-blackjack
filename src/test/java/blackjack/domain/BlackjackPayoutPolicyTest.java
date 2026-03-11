package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.bet.BlackjackPayoutPolicy;
import blackjack.domain.bet.PayoutPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackPayoutPolicyTest {

    @Test
    @DisplayName("베팅 금액의 1.5배로 계산한다.")
    void 베팅_금액의_1_5배로_계산한다() {
        int amount = 20000;
        PayoutPolicy blackjackPayoutPolicy = new BlackjackPayoutPolicy();
        int expected = 30000;

        int actual = blackjackPayoutPolicy.payout(amount);

        assertThat(actual).isEqualTo(expected);
    }
}
