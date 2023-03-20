package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BetAmountTest {

    @DisplayName("베팅 금액은 1,000원에서 1,000,000원 사이가 아니라면 예외처리한다.")
    @ParameterizedTest(name = "{index} : {0}을 넣으면 {1}을 반환한다.")
    @CsvSource({"999, true", "1000, false", "1000000, false", "1000001, true"})
    void validateBetAmount(int betAmount, boolean doThrow) {
        if (doThrow) {
            assertThatThrownBy(() -> BetAmount.of(betAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BetAmount.VALID_AMOUNT_MSG);
            return;
        }

        assertDoesNotThrow(() -> BetAmount.of(betAmount));
    }
}
