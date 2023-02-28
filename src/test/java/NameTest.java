import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import domain.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NameTest {

    @Nested
    @DisplayName("이름의 길이가 10글자 이하여야 이름을 생성할 수 있다.")
    class LengthTest {
        @Test
        void validLengthTest() {
            assertDoesNotThrow(() -> new Name("mango"));
        }

        @Test
        void invalidLengthTest() {
            assertThatThrownBy(() -> new Name("abcdefghijklm"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 이름의 길이는 10글자 이하여야 합니다.");
        }
    }

}
