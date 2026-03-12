package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BetTest {

    @Test
    @DisplayName("배팅 금액을 저장한다.")
    void setAmountTest() {
        // given
        int amount = 3000;

        // when
        Bet bet = Bet.of(amount);

        // then
        assertNotNull(bet);
    }

    @Test
    @DisplayName("배팅 금액은 양수여야 한다.")
    void setAmount_validateNonNegativeAmountTest() {
        // given
        int amount = -3000;

        // when - then
        assertThrows(IllegalArgumentException.class, () -> Bet.of(amount));
    }

    @Test
    @DisplayName("배팅 금액은 최대 배팅 금액을 초과할 수 없다.")
    void setAmount_validateMaxBetAmountTest() {
        // given
        int overBetAmount = 1000_000_000;

        // when - then
        assertThrows(IllegalArgumentException.class, () -> Bet.of(overBetAmount));
    }

    @Test
    @DisplayName("배팅 금액은 1000원 단위로 입력해야 한다.")
    void setAmount_validateMaxBetUnitTest() {
        // given
        int amount = 10500;

        // when - then
        assertThrows(IllegalArgumentException.class, () -> Bet.of(amount));
    }

    @ParameterizedTest
    @DisplayName("배팅 금액을 승패 결과에 따라 계산한다.")
    @CsvSource({
            "LOSE, false, -3000",
            "DRAW, false, 0",
            "WIN, false, 3000",
            "WIN, true, 4500"
    })
    void calculateProfitTest(MatchResult matchResult, boolean isBlackJack, int expectedProfit) {
        // given
        int amount = 3000;

        // when
        Bet bet = Bet.of(amount);

        // then
        Assertions.assertEquals(expectedProfit, bet.calculateProfit(matchResult, isBlackJack));
    }
}
