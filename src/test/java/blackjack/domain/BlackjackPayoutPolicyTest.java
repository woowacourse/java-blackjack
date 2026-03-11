package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackPayoutPolicyTest {

    @Test
    @DisplayName("베팅 금액의 1.5배로 계산한다.")
    void 베팅_금액의_1_5배로_계산한다() {
        Bet bet = new Bet(10000);
        int expected = 15000;

        int actual = bet.calculatePayout();

        assertThat(actual).isEqualTo(expected);
    }
}
