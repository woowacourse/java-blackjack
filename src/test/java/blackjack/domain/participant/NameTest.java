package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.participant.Name.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("예외 테스트: 생성자에 Null, Empty, 5자 초과 String이 전달되는 경우 Exception 발생")
    @Test
    void test1() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_NAME_ERR_MSG);

        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(EMPTY_NAME_ERR_MSG);

        assertThatThrownBy(() -> new Name("5자초과이름"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MAX_NAME_LENGTH_ERR_MSG);
    }
}
