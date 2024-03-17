package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.Name.NAME_LENGTH_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("참가자의 이름은 1글자 이상 5글자 이하이다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "abcde"})
    void createSuccess(String input) {
        assertThatCode(() -> new Name(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("이름이 1글자 미만, 5글자 초과면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef"})
    void validateLength(String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAME_LENGTH_MESSAGE);
    }
}
