package view;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InputValidatorTest {

    @ParameterizedTest(name = "빈 문자열이 입력되면 예외를 발생시킨다.")
    @ValueSource(strings = {"", " "})
    void validateNotBlank(String target) {
        InputValidator inputValidator = new InputValidator();

        assertThatThrownBy(() -> inputValidator.validateNotBlank(target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}