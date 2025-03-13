package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProfitRateTest {

    @ParameterizedTest
    @DisplayName("배율에 따른 수익을 계산할 수 있다.")
    @MethodSource()
    void calculateProfit(ProfitRate rate, int amount, int expectedProfit) {
        int actualProfit = rate.calculateProfit(amount);
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    static Stream<Arguments> calculateProfit() {
        return Stream.of(
                Arguments.of(ProfitRate.BLACKJACK_WITH_INITIAL_HAND, 1000, 1500),
                Arguments.of(ProfitRate.WIN, 1000, 1000),
                Arguments.of(ProfitRate.LOSE, 1000, -1000),
                Arguments.of(ProfitRate.DRAW, 1000, 0)
        );
    }
}