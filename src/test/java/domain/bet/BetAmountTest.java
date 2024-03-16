package domain.bet;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @DisplayName("배팅 금액은 500원 미만일 수 없다.")
    @Test
    void minBetAmount() {
        assertThatThrownBy(() -> new BetAmount(499)).isInstanceOf(IllegalArgumentException.class);
    }
}
