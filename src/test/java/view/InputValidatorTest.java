package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {
    @Test
    @DisplayName("빈 값이 들어오면 예외처리한다.")
    void shouldThrowInputIsEmpty() {
        assertThatThrownBy(() -> InputValidator.validateBlank(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InputValidator.INPUT_EMPTY_ERROR_MESSAGE);
    }
}
