package util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {
    @Nested
    @DisplayName("예외 케이스")
    class failure {

        @DisplayName("입력값이 비어 있을 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        void 입력값이_비어_있을_경우_예외가_발생한다(String input) {
            assertThatThrownBy(() -> InputValidator.validatePlayerNameInput(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 입력값은 비어 있을 수 없습니다.");
        }

        @DisplayName("쉼표와 공백을 뺀 특수문자가 존재할 때 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"pobi+", "jason.stark"})
        public void 쉼표_공백을_제외한_특수문자가_있을_경우_예외를_발생시킨다(String input) {
            assertThatThrownBy(() -> InputValidator.validatePlayerNameInput(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 쉼표(,)를 제외한 특수문자는 사용할 수 없습니다.");
        }
    }
}
