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
}
