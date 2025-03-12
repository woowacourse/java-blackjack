package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("입력 검증 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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

    @ParameterizedTest
    @ValueSource(strings = {" ", "만원", "10000원", "10000won"})
    void 베팅_금액을_입력하지_않거나_숫자를_입력하지_않으면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateNumberFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 숫자로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"10000", "20000", "0", "5000"})
    void 베팅_금액을_숫자로_입력하면_예외가_발생하지_않는다(String input) {
        assertThatCode(() -> InputValidator.validateNumberFormat(input))
                .doesNotThrowAnyException();
    }
}
