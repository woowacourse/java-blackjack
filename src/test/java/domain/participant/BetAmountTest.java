package domain.participant;

import domain.Bet.BetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BetAmountTest {

    @DisplayName("배팅 금액은 0보다 커야한다.")
    @Test
    void minBetAmount() {
        assertThatThrownBy(() -> BetAmount.from(500)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액 * 수익 배수를의 값을 계산한다.")
    @Test
    void multiply() {
        BetAmount betAmount = BetAmount.from(1000);
        BetAmount profit = betAmount.multiply(1.5);

        assertThat(profit.getValue()).isEqualTo(1500);
    }
}
