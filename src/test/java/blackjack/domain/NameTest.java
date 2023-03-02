package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameTest {

    @Test
    @DisplayName("이름 초기화 테스트")
    void nameInitTest() {
        assertDoesNotThrow(() -> new Name("test"));
    }

    @Test
    @DisplayName("공백이 입력되면 예외 발생 테스트")
    void blankNameExceptionTest() {
        assertThatThrownBy(() -> new Name(" ")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.BLANK_NAME_EXCEPTION_MESSAGE);
    }

}
