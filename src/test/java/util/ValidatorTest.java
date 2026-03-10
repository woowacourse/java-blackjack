package util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ValidatorTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
    void 빈_카드_수령_여부_예외_테스트() {
        // given
        String answer = "";

        // when & then
        assertThatThrownBy(() ->
                Validator.validateGetMoreEmptyInput(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
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
