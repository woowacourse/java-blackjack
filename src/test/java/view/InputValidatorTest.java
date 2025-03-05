package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("입력 검증 테스트")
public class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {",a", "pobi|jason", "pobi,jason,", " "})
    void 이름_형식을_위반한_경우_예외발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateNameFormat(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] (,)로 구분된 한글자 이상의 영어 이름을 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "pobi,jason", "pobi,jason,drago"})
    void 이름_형식을_위반하지_않은_경우_예외발생하지_않는다(String input) {
        assertThatCode(() -> InputValidator.validateNameFormat(input))
            .doesNotThrowAnyException();
    }
}
