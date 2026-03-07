package util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @ParameterizedTest
    @ValueSource(strings = {"y", "Y", "n", "N"})
    @DisplayName("대소문자 상관없이 y/n을 입력하는 경우, 정상 동작한다.")
    void y_n_정상_입력_테스트(String answer) {
        assertThatCode(() -> Validator.validateEmptyAnswerInput(answer))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 수령 여부(y/n)가 비어있는 경우, IllegalArgumentException이 발생한다.")
    void 빈_카드_수령_여부_예외_테스트() {
        // given
        String answer = "";

        // when & then
        assertThatThrownBy(() ->
                Validator.validateEmptyAnswerInput(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    @DisplayName("y/n 외의 문자를 입력하는 경우, IllegalArgumentException이 발생한다.")
    void y_n_외의_문자_입력_테스트() {
        // given
        String answer = "d";

        // when & then
        assertThatThrownBy(() ->
                Validator.validateYesOrNo(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
