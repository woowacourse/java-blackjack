package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BetAmountTest {
    
    @DisplayName("배팅 금액은 0보다 커야한다.")
    @Test
    void minBetAmount() {
        assertThatThrownBy(() -> BetAmount.from(500)).isInstanceOf(IllegalArgumentException.class);
    }
}
