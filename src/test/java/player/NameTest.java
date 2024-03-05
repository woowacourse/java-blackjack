package player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"12", "12345"})
    @DisplayName("이름이 잘 생성된다.")
    void validNameTest(String name) {
        assertDoesNotThrow(() -> new Name(name));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름이 공백으로 이루어진 경우 예외를 발생시킨다.")
    void blankNameTest(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abcdef"})
    @DisplayName("이름이 길이 제한을 어기는 경우 예외를 발생시킨다.")
    void longNameTest(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 2글자 이상 5글자 이하여야 합니다.");
    }
}
