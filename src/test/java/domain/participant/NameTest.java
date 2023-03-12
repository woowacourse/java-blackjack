package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class NameTest {

    @Nested
    @DisplayName("이름의 길이가 1글자 이상, 10글자 이하여야 이름을 생성할 수 있다.")
    class LengthTest {
        @DisplayName("이름을 생성할 수 있다.")
        @Test
        void validLengthTest() {
            Name name = new Name("mango");

            assertThat(name.getName()).isEqualTo("mango");
        }

        @DisplayName("10글자를 초과하는 문자로는 이름을 생성할 수 없다.")
        @Test
        void invalidOverLengthTest() {
            assertThatThrownBy(() -> new Name("abcdefghijklm"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(Name.LENGTH_ERROR_MESSAGE);
        }

        @DisplayName("이름에는 null이나 빈 값이 들어올 수 없다.")
        @ParameterizedTest
        @NullAndEmptySource
        void invalidLengthTestWithNull(String inputName) {
            assertThatThrownBy(() -> new Name(inputName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(Name.NULL_ERROR_MESSAGE);
        }
    }

    @Nested
    @DisplayName("이름에는 숫자나 특수문자가 들어갈 수 없다.")
    class FormatTest {
        @DisplayName("이름에 숫자나 특수문자가 들어가지 않으면 생성에 성공한다.")
        @Test
        void validFormatTest() {
            assertDoesNotThrow(() -> new Name("숫자가없는이름"));
        }

        @DisplayName("이름에 숫자가 들어가면 예외 처리한다.")
        @Test
        void invalidFormatWithNumberTest() {
            assertThatThrownBy(() -> new Name("이름4"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(Name.FORMAT_ERROR_MESSAGE);
        }

        @DisplayName("이름에 특수 문자가 들어가면 예외 처리한다.")
        @Test
        void invalidFormatWithSpecialCharacterTest() {
            assertThatThrownBy(() -> new Name("특수^"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(Name.FORMAT_ERROR_MESSAGE);
        }
    }
}
