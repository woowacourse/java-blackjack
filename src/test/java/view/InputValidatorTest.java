package view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Constants;

public class InputValidatorTest {
    @Test
    @DisplayName("빈 값이 들어오면 예외처리한다.")
    void shouldThrowInputIsEmpty() {
        Assertions.assertThatThrownBy(() -> InputValidator.validateBlank(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.ERROR_PREFIX + "입력값이 존재하지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"가", "가1", "A", "A1", "!"})
    @DisplayName("배팅 금액이 숫자가 아니면 예외 처리한다.")
    void inputBettingMoneyNotNumeric(String bettingMoney) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateBettingMoney(bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.ERROR_PREFIX + "배팅 금액은 숫자만 가능합니다.");

    }
}
