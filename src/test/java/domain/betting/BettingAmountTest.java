package domain.betting;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    @Test
    void 베팅_금액이_음수면_예외가_발생한다() {
        BigDecimal testBettingAmount = BigDecimal.valueOf(-1000);
        assertThrows(IllegalArgumentException.class, () -> {
            new BettingAmount(testBettingAmount);
        });
    }

    @Test
    void 베팅_금액이_양수이면_생성된다() {
        BigDecimal testBettingAmount = BigDecimal.valueOf(1000);
        assertDoesNotThrow(() -> {
            new BettingAmount(testBettingAmount);
        });
    }

    @Test
    void 문자열로도_베팅금액을_생성할_수_있다() {
        BettingAmount bettingAmount = new BettingAmount("1000");

        assertEquals(0, BigDecimal.valueOf(1000).compareTo(bettingAmount.getMoney()));
    }

    @Test
    void 숫자가_아닌_문자열이면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BettingAmount("abc");
        });
    }
}
