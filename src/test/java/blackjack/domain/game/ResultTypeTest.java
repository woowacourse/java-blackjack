package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ResultTypeTest {

    private static final int TYPES_LENGTH = 4;

    @DisplayName("WIN 의 profitCalculator 의 apply 메소드는 전달된 정수를 그대로 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "1000,1000", "3000,3000"})
    void calculateProfit_returnsSameValue(int input, int expected) {
        // given & when
        int actual = ResultType.WIN.profitCalculator.apply(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("WIN_WITH_BLACKJACK 의 profitCalculator 의 apply 메소드는 전달된 정수에 1.5를 곱해 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "1000,1500", "3000,4500"})
    void calculateProfit_returnsValueMultipliedOnePointFive(int input, int expected) {
        // given & when
        int actual = ResultType.WIN_WITH_BLACKJACK.profitCalculator.apply(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("WIN_WITH_BLACKJACK 의 profitCalculator 의 apply 메소드는 계산결과를 반올림하여 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1555,2333", "2333,3500"})
    void calculateProfit_returnsRoundedValueMultipliedOnePointFive(int input, int expected) {
        // given & when
        int actual = ResultType.WIN_WITH_BLACKJACK.profitCalculator.apply(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("LOSE 의 profitCalculator 의 apply 메소드는 전달된 정수의 부호를 반전하여 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "1000,-1000", "3000,-3000", "-1000,1000"})
    void calculateProfit_returnsNegativeValue(int input, int expected) {
        // given & when
        int actual = ResultType.LOSE.profitCalculator.apply(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("DRAW 의 profitCalculator 의 apply 메소드는 항상 0을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 2000, -1000})
    void calculateProfit_returnsAlwaysZero(int input) {
        // given & when
        int actual = ResultType.DRAW.profitCalculator.apply(input);

        // then
        assertThat(actual).isZero();
    }

}
