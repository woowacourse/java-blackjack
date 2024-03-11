package blackjack.domain.result;

import blackjack.exception.BettingPriceOutOfBoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}