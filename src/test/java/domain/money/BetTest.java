package domain.money;

import domain.match.MatchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BetTest {

    @Test
    @DisplayName("배팅 금액을 저장한다.")
    void setAmountTest() {
        // given
        int amount = 3000;

        // when
        Bet bet = new Bet(amount);

        // then
        assertNotNull(bet);
    }

    @Test
    @DisplayName("배팅 금액은 양의 정수여야 한다.")
    void validateAmount_PositiveTest() {
        // given
        int amount = 1;

        // when - then
        assertDoesNotThrow(() -> new Bet(amount));
    }

    @Test
    @DisplayName("배팅 금액은 양의 정수여야 한다.")
    void validateAmount_NegativeTest() {
        // given
        int amount = -1;

        // when - then
        assertThrows(IllegalArgumentException.class, () -> new Bet(amount));
    }

    @Test
    @DisplayName("배팅 금액은 양의 정수여야 한다.")
    void validateAmount_zeroTest() {
        // given
        int amount = 0;

        // when - then
        assertThrows(IllegalArgumentException.class, () -> new Bet(amount));
    }

    @Test
    @DisplayName("배팅 금액은 최대 배팅 금액을 초과할 수 없다.")
    void validateMaxBetAmountTest() {
        // given
        int overBetAmount = 1_000_000_000; // MAX_BET_AMOUNT = 1억

        // when - then
        assertThrows(IllegalArgumentException.class, () -> new Bet(overBetAmount));
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
        Bet bet = new Bet(3000);

        // when
        Money profit = bet.calculateProfit(matchResult, isBlackJack);

        // then
        Assertions.assertEquals(Money.of(expectedProfit), profit);
    }
}
