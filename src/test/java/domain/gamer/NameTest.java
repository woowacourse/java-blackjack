package domain.gamer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("이름의 길이가 1 미만, 5초과면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "liniri"})
    void invalidNameLengthTest(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.INVALID_NAME_LENGTH);
    }

}
