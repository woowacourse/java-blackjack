package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Nested
    @DisplayName("생성자는")
    class Constructor {

        @Test
        @DisplayName("공백 또는 빈 값이면 예외를 발생시킨다.")
        void throwExceptionOnBlank() {
            Assertions.assertThatThrownBy(() -> new Name(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백 또는 빈 값이면 안됩니다.");
        }

    }
}
