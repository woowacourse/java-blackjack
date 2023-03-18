package domain.game;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @Test
    @DisplayName("수익을 계산한다.")
    void calculateProfit() {
        BetAmount betAmount = new BetAmount(10000);
        assertThat(betAmount.calculateProfit(1.5)).isEqualTo(15000);
    }
}
