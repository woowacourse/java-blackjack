package blackjack.domain.result;

import blackjack.exception.BettingPriceOutOfBoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BettingPriceTest {
    @DisplayName("베팅 금액이 지정된 범위를 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, -1, 10, 100_001, 100_000_000})
    void bettingPriceOutOfBoundTest(int bettingPrice) {
        assertThatThrownBy(() -> new BettingPrice(bettingPrice))
                .isInstanceOf(BettingPriceOutOfBoundException.class);
    }

    @DisplayName("베팅 금액이 지정된 범위 내에 있으면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {100_000, 1_000, 5_000, 10_000})
    void validBettingPriceBoundTest(int bettingPrice) {
        assertThatCode(() -> new BettingPrice(bettingPrice))
                .doesNotThrowAnyException();
    }

    @DisplayName("HandResult의 수익률에 따라 올바른 수익을 계산한다.")
    @ParameterizedTest
    @MethodSource("provideBettingPriceWithProfit")
    void calculateProfitTest(BettingPrice bettingPrice, HandResult handResult, double expectedProfit) {
        double actualProfit = bettingPrice.calculateProfit(handResult);
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> provideBettingPriceWithProfit() {
        return Stream.of(
                Arguments.of(new BettingPrice(1_000), HandResult.BLACKJACK_WIN, 1_500),
                Arguments.of(new BettingPrice(5_000), HandResult.DRAW, 0),
                Arguments.of(new BettingPrice(10_000), HandResult.WIN, 10_000),
                Arguments.of(new BettingPrice(100_000), HandResult.LOSE, -100_000)
        );
    }
}
