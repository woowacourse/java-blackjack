package domain.betting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BettingAmountTest {

    @Test
    void 베팅_금액이_음수면_예외가_발생한다() {
        Integer testBettingAmount = -1000;
        assertThrows(IllegalArgumentException.class, () -> {
            new BettingAmount(testBettingAmount);
        });
    }

    @Test
    void 베팅_금액이_양수이면_생성된다() {
        Integer testBettingAmount = 1000;
        assertDoesNotThrow(() -> {
            new BettingAmount(testBettingAmount);
        });
    }
}
