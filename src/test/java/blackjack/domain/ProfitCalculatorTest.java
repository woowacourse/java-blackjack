package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.gambler.Name;
import blackjack.view.WinningType;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ProfitCalculatorTest {
    @DisplayName("플레이어의_수익금을_계산한다")
    @CsvSource(value = {"BLACKJACK_WIN:5_000", "BLACKJACK_DRAW:0", "WIN:10_000", "DEFEAT:-10_000", "DRAW:0"},
            delimiterString = ":")
    @ParameterizedTest
    void calculatePlayerProfit(WinningType type, int expected) {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculatePlayerProfit(type, new BettingAmount(10_000));

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("딜러의_수익금은_플레이어의_수익/손실으로부터_계산한다")
    @MethodSource("returnPlayersProfitAndExpected")
    @ParameterizedTest
    void calculateDealerProfit(Map<Name, Integer> playersProfit, int expected) {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculateDealerProfit(playersProfit);

        // then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> returnPlayersProfitAndExpected() {
        return Stream.of(Arguments.arguments(createPlayerProfit(-20_000, 10_000, 20_000), -10_000),
                Arguments.arguments(createPlayerProfit(-20_000, -10_000, -20_000), 50_000),
                Arguments.arguments(createPlayerProfit(-20_000, 20_000), 0));
    }

    private static Map<Name, Integer> createPlayerProfit(int... profits) {
        Map<Name, Integer> playersProfit = new HashMap<>();
        int index = 0;
        for (int profit : profits) {
            Name name = new Name("레오" + index++);
            playersProfit.put(name, profit);
        }
        return playersProfit;
    }
}
