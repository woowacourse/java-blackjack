package blackjack.view;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @ParameterizedTest
    @NullSource
    @EmptySource
    @DisplayName("입력된 값이 널,공백이 있을 시 예외 발생")
    void checkNull(String input) {
        InputValidator inputValidator = new InputValidator();
        assertThatThrownBy(() -> {inputValidator.validateInput(input);})
            .isInstanceOf(IllegalArgumentException.class);
    }
}