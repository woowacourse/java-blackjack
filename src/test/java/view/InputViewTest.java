package view;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    @Nested
    @DisplayName("사용자에게 이름을 입력 받는 테스트")
    class ReadNames {
        @DisplayName("interface Reader로부터 받은 String을 List<String>으로 반환한다.")
        @Test
        void stringToList() {
            Assertions.assertThat(InputView.readNames(() -> "a,b,c"))
                    .isEqualTo(List.of("a", "b", "c"));
        }

        @DisplayName("null 혹은 빈 문자열을 받으면 예외를 발생한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void emptyInputException(String input) {
            Assertions.assertThatThrownBy(() -> InputView.readNames(() -> input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름에 공백이나 null을 넣을 수 없습니다.");
        }

        @DisplayName("쉼표로 구분된 이름이 null 혹은 빈 문자열이면 예외를 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"a,b,"})
        void emptyNameException(String input) {
            Assertions.assertThatThrownBy(() -> InputView.readNames(() -> input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름에 공백이나 null을 넣을 수 없습니다.");
        }

        @Test
        @DisplayName("중복된 이름이 있으면 예외를 발생한다.")
        void duplicateNameException() {
            Assertions.assertThatThrownBy(() -> InputView.readNames(() -> "a,a,a"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 중복될 수 없습니다.");
        }

        @DisplayName("공백을 제거한 이름을 반환한다.")
        @Test
        void nameWithSpaces() {
            Assertions.assertThat(InputView.readNames(() -> "a , b , c"))
                    .isEqualTo(List.of("a", "b", "c"));
        }
    }

    @Nested
    @DisplayName("사용자에게 대답(y 또는 n) 입력 받는 테스트")
    class ReadAnswer {
        @DisplayName("null 혹은 빈 문자열을 받으면 예외를 발생한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void emptyInputException(String input) {
            Assertions.assertThatThrownBy(() -> InputView.readAnswer(() -> input, "test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름에 공백이나 null을 넣을 수 없습니다.");
        }

        @Test
        @DisplayName("y 또는 n이 아닌 문자가 나올 경우 예외 발생")
        void wrongAnswer() {
            Assertions.assertThatThrownBy(() -> InputView.readAnswer(() -> "a", "test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("y또는 n만 입력 받을 수 있습니다.");
        }
    }
}
