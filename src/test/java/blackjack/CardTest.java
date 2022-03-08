package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CardTest {

    @Test
    @DisplayName("범위 밖의 숫자로 생성시 예외를 발생한다.")
    void exceptionNumberOutOfRange() {
        assertThatThrownBy(() -> new Card(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
