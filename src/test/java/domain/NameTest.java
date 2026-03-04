package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.ExceptionMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"b", "abcdefgh"})
    void 이름의_길이가_범위에서_벗어나는_경우_예외_발생(String name) {
        // when & then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_NAME_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"나는A", "안녕하세요"})
    void 이름의_형식이_맞지_않는_경우_예외_발생(String name) {
        // when & then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_NAME_FORMAT.getMessage());
    }
}
