package domain.player.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1000 -> 1000",
            "1500 -> 1500",
            "2000 -> 2000"
    }, delimiterString = " -> ")
    @DisplayName("breakEven() : 그대로 값을 돌려주다.")
    void test_breakEven(final int bettingAmount, final int resultAmount) throws Exception {
        //given
        final Money bettingMoney = Money.wons(bettingAmount);
        final Money resultMoney = Money.wons(resultAmount);

        //when & then
        assertEquals(bettingMoney.breakEven(), resultMoney);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000,1.5,1500",
            "1500,1.5,2250",
            "1800,1.8,3240",
            "2231,1.75,3904.25"
    })
    @DisplayName("times() : 현재 돈에 주어진 percent 를 계산할 수 있다.")
    void test_times(final int bettingAmount, final double percent, final double resultAmount) throws Exception {
        //given
        final Money bettingMoney = Money.wons(bettingAmount);
        final Money resultMoney = Money.wons(resultAmount);

        //when & then
        assertEquals(bettingMoney.times(percent), resultMoney);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000 -> -1000",
            "1500 -> -1500",
            "2000 -> -2000"
    }, delimiterString = " -> ")
    @DisplayName("lose() : 현재 가지고 있는 돈을 잃을 수 있다.")
    void test_lose(final int bettingAmount, final int resultAmount) throws Exception {
        //given
        final Money bettingMoney = Money.wons(bettingAmount);
        final Money resultMoney = Money.wons(resultAmount);

        //when & then
        assertEquals(bettingMoney.lose(), resultMoney);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000,1000,2000",
            "1500,1000,2500",
            "2000,1028,3028"
    })
    @DisplayName("plus() : 돈을 합할 수 있다.")
    void test_plus(final int amount1, final int amount2, final int resultAmount) throws Exception {
        //given
        final Money origin = Money.wons(amount1);
        final Money other = Money.wons(amount2);

        //when & then
        assertEquals(origin.plus(other), Money.wons(resultAmount));
    }
}
