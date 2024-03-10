package domain.gamer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.NameBlankException;
import exception.NameLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("이름의 길이가 1 미만, 5초과면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "liniri"})
    void invalidNameLengthTest(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(NameLengthException.class)
                .hasMessage(NameLengthException.INVALID_NAME_LENGTH);
    }

    @DisplayName("이름을 공백으로 사용하면 예외를 던진다.")
    @Test
    void nameBlankTest() {
        // given
        String name = " ";

        // then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(NameBlankException.class)
                .hasMessage(NameBlankException.NOT_ALLOWED_BLANK_NAME);
    }
}
