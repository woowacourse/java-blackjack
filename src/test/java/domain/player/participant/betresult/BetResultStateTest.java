package domain.player.participant.betresult;

import domain.player.participant.Money;
import domain.player.participant.betresult.resultstate.BetResultState;
import domain.player.participant.betresult.resultstate.BreakEvenState;
import domain.player.participant.betresult.resultstate.LoseState;
import domain.player.participant.betresult.resultstate.WinState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BetResultStateTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1000 -> 1500",
            "1500 -> 2250",
            "1800 -> 2700",
            "2231 -> 3346.5",
            "2249 -> 3373.5"
    }, delimiterString = " -> ")
    @DisplayName("calculateBetOutComeOf(WinState) : 게임에서 이길 경우 돈을 percent 만큼 더 얻는다.")
    void test_calculateBetOutComeOf_WinState(final int bettingAmount,
                                             final double resultAmount) throws Exception {
        //given
        BetResultState winState = new WinState();

        final Money bettingMoney = Money.wons(bettingAmount);
        final Money resultMoney = Money.wons(resultAmount);

        //when & then
        assertEquals(winState.calculateBetOutcomeOf(bettingMoney), resultMoney);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000 -> -1000",
            "1500 -> -1500",
            "2000 -> -2000"
    }, delimiterString = " -> ")
    @DisplayName("calculateBetOutComeOf(LoseState) : 게임에서 질 경우 돈을 전부 잃는다.")
    void test_lose(final int bettingAmount, final int resultAmount) throws Exception {
        //given
        BetResultState loseState = new LoseState();

        final Money bettingMoney = Money.wons(bettingAmount);
        final Money resultMoney = Money.wons(resultAmount);

        //when & then
        assertEquals(loseState.calculateBetOutcomeOf(bettingMoney), resultMoney);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000 -> 1000",
            "1500 -> 1500",
            "2000 -> 2000"
    }, delimiterString = " -> ")
    @DisplayName("calculateBetOutComeOf(BreakEven) : 게임에서 무승부일 경우 본전이다.")
    void test_breakEven(final int bettingAmount, final int resultAmount) throws Exception {
        //given
        BetResultState breakEvenState = new BreakEvenState();

        final Money bettingMoney = Money.wons(bettingAmount);
        final Money resultMoney = Money.wons(resultAmount);

        //when & then
        assertEquals(breakEvenState.calculateBetOutcomeOf(bettingMoney), resultMoney);
    }
}
