package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningTypeTest {
    @DisplayName("금액에_수익률을_곱해_반환할_수_있다")
    @CsvSource(value = {"BLACKJACK_WIN:15_000", "WIN:20_000", "DEFEAT:-10_000", "DRAW:10_000"}, delimiterString = ":")
    @ParameterizedTest
    void multiplyProfitRate(WinningType type, int expected) {
        // when
        int result = type.multiplyProfitRate(10_000);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
