import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NameTest {

    @Nested
    @DisplayName("이름의 길이가 10글자 이하여야 이름을 생성할 수 있다.")
    class LengthTest {
        @DisplayName("이름을 생성할 수 있다.")
        @Test
        void validLengthTest() {
            assertDoesNotThrow(() -> new Name("mango"));
        }

        @DisplayName("10글자를 초과하는 문자로는 이름을 생성할 수 없다.")
        @Test
        void invalidLengthTest() {
            assertThatThrownBy(() -> new Name("abcdefghijklm"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 이름의 길이는 10글자 이하여야 합니다.");
        }
    }

    @DisplayName("이름에는 숫자나 특수문자가 들어갈 수 없다.")
    @Test
    void nameRegexTest() {
        assertDoesNotThrow(() -> new Name("숫자가없는이름"));
        assertThatThrownBy(() -> new Name("444"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Name("이름4"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Name("특수^"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
