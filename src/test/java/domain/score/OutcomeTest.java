package domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.score.Outcome.LOSE;
import static domain.score.Outcome.*;
import static domain.score.RevenueCalculator.*;
import static org.assertj.core.api.Assertions.assertThat;

class OutcomeTest {

    @Test
    @DisplayName("이기면 돈을 번다.")
    void getRevenueCalculator_Win_Earn() {
        assertThat(WIN.getRevenueCalculator()).isEqualTo(EARN);
    }

    @Test
    @DisplayName("지면 돈을 잃는다.")
    void getRevenueCalculator_Lose_Lose() {
        assertThat(LOSE.getRevenueCalculator()).isEqualTo(RevenueCalculator.LOSE);
    }

    @Test
    @DisplayName("비기면 돈을 돌려받는다.")
    void getRevenueCalculator_Tie_Return() {
        assertThat(TIE.getRevenueCalculator()).isEqualTo(RETURN);
    }

    @Test
    @DisplayName("블랙잭이면 블랙잭 기준으로 수익을 계산한다.")
    void getRevenueCalculator_Blackjack_BlackjackEarn() {
        assertThat(BLACKJACK.getRevenueCalculator()).isEqualTo(BLACKJACK_EARN);
    }
}
