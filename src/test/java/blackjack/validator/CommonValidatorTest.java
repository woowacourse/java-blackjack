package blackjack.validator;

import static blackjack.validator.CommonValidator.NOT_ALLOW_BLANK_MESSAGE;
import static blackjack.validator.CommonValidator.NOT_ALLOW_NUMBER_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommonValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("공백은 허용하지 않는다")
    void not_allow_space(String value) {
        assertThatThrownBy(() -> CommonValidator.isBlank(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ALLOW_BLANK_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "#"})
    @DisplayName("숫자여야 한다")
    void not_allow_no_number(String value) {
        assertThatThrownBy(() -> CommonValidator.isNumber(value))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage(NOT_ALLOW_NUMBER_MESSAGE);
    }
}
