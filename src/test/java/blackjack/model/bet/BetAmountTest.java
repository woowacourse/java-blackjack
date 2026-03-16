package blackjack.model.bet;

import static blackjack.model.bet.BetAmount.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "10"})
    @DisplayName("배팅 금액 정상 생성")
    void createBetAmount_success(String input) {
        //when & then
        assertDoesNotThrow(() -> new BetAmount(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("입력값이 공백일 경우 예외 발생")
    void createBetAmount_fail_when_input_is_empty(String emptyInput) {
        //when & then
        Assertions.assertThatThrownBy(() -> new BetAmount(emptyInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_EMPTY_INPUT);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", "123*"})
    @DisplayName("입력값이 숫자 형태가 아닐 경우 예외 발생")
    void createBetAmount_fail_when_input_is_not_number(String stringInput) {
        //when & then
        Assertions.assertThatThrownBy(() -> new BetAmount(stringInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_BET_AMOUNT_NOT_INTEGER);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    @DisplayName("입력값이 1원 이하일 경우 예외 발생")
    void createBetAmount_fail_when_input_is_not_positive(String negativeInput) {
        //when & then
        Assertions.assertThatThrownBy(() -> new BetAmount(negativeInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_BET_AMOUNT_NOT_POSITIVE);
    }
}