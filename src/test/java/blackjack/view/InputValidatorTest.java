package blackjack.view;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @ParameterizedTest
    @ValueSource(strings = {"Y","N","가", "yy", "nn"})
    @DisplayName("y나 n이 아닌 명령어가 들어오면 예외 발생")
    void checkHitCommand(String hitCommand) {
        InputValidator inputValidator = new InputValidator();
        assertThatThrownBy(() -> inputValidator.checkHitCommand(hitCommand))
            .isInstanceOf(IllegalArgumentException.class);
    }
}