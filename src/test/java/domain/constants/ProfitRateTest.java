package domain.constants;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitRateTest {
    @DisplayName("베팅 금액에 따른 수익을 계산한다.")
    @Test
    void getProfit() {
        // given
        int betAmount = 10000;

        // when
        int blackjackProfit = ProfitRate.BLACKJACK.getProfit(betAmount);
        int winProfit = ProfitRate.WIN.getProfit(betAmount);
        int pushProfit = ProfitRate.PUSH.getProfit(betAmount);
        int loseProfit = ProfitRate.LOSE.getProfit(betAmount);

        // then
        assertAll(
                () -> assertEquals(15000, blackjackProfit),
                () -> assertEquals(10000, winProfit),
                () -> assertEquals(0, pushProfit),
                () -> assertEquals(-10000, loseProfit)
        );
    }
}
