package blackjack.view;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class InputValidatorTest {
    @ParameterizedTest
    @NullSource
    @EmptySource
    @DisplayName("입력된 값이 널,공백이 있을 시 예외 발생")
    void checkNull(String input) {
        InputValidator inputValidator = new InputValidator();
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            inputValidator.validateInput(input);
        });
    }
}