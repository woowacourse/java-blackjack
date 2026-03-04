package util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {
    @ParameterizedTest
    @DisplayName("입력값이 비어 있을 경우 예외가 발생한다.")
    @ValueSource(strings = {"", " "})
    void 입력값이_비어_있을_경우_예외가_발생한다(String input) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력값은 비어 있을 수 없습니다.");
    }
}
