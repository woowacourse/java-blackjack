package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SelectionTest {

    @Nested
    @DisplayName("from은")
    class From {
        @Test
        @DisplayName("y, n 외의 문자가 들어올 경우 예외를 발생시킨다.")
        void throwExceptionOnOtherString() {
            Assertions.assertThatThrownBy(() -> Selection.from("t"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력하여야 합니다.");
        }

    }
}
