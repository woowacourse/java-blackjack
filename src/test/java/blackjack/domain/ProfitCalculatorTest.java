package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.view.WinningType;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ProfitCalculatorTest {
    @DisplayName("플레이어가_블랙잭_승리한_경우_수익금은_원금의_1.5배다")
    @CsvSource(value = {"BLACKJACK_WIN:15_000", "WIN:20_000", "DEFEAT:-10_000", "DRAW:10_000"}, delimiterString = ":")
    @ParameterizedTest
    void calculatePlayerProfit(WinningType type, int expected) {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculatePlayerProfit(type, 10_000);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("딜러의_수익금은_플레이어의_수익/손실으로부터_계산한다")
    @MethodSource("returnPlayersProfitAndExpected")
    @ParameterizedTest
    void calculateDealerProfit(List<Integer> playersProfit, int expected) {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculateDealerProfit(playersProfit);

        // then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> returnPlayersProfitAndExpected() {
        return Stream.of(Arguments.arguments(List.of(-20_000, 10_000, 20_000), -10_000),
                Arguments.arguments(List.of(-20_000, -10_000, -20_000), 50_000),
                Arguments.arguments(List.of(-20_000, 20_000), 0));
    }
}
