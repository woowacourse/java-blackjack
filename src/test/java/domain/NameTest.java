package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {
    @Test
    @DisplayName("생성자로 받은 이름 문자열을 반환한다")
    void getName() {
        Assertions.assertThat(new Name("이름").getName())
                .isEqualTo("이름");
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "  ", "\t", "\n"})
    @DisplayName("이름에 공백이나 null이 들어가면 예외를 발생한다")
    void emptyExceptionTest(String input) {
        Assertions.assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름에 공백이나 null을 넣을 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 5글자를 넘으면 예외를 발생한다")
    void validateLength() {
        Assertions.assertThatThrownBy(() -> new Name("123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 5글자 이하여야 합니다.");
    }

    @Test
    @DisplayName("이름이 5글자를 넘으면 예외를 발생한다")
    void validateCharacter() {
        Assertions.assertThatThrownBy(() -> new Name("$#@$"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 알파벳, 한글, 숫자, '_', '-'로만 이루어져야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"한글이름", "abcd", "1234", "--_-"})
    @DisplayName("적절한 문자로 이름을 생성하면 예외를 발생하지 않는다.")
    void createName(String input) {
        Assertions.assertThatCode(() -> new Name(input))
                .doesNotThrowAnyException();
    }
}
