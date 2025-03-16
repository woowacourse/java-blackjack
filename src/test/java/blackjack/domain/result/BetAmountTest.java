package blackjack.domain.result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BetAmountTest {
    @Test
    void 배팅_금액을_저장한다() {
        // given
        BetAmount betAmount = new BetAmount(1_000);

        // when
        int amount = betAmount.getAmount();

        // then
        assertEquals(1_000, amount);
    }
}
