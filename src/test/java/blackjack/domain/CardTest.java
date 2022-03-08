package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드를 생성한다")
    void create() {
        Assertions.assertDoesNotThrow(() -> new Card(Denomination.TWO, Suit.SPADE));
    }

    @Test
    @DisplayName("카드를 생설할때 null 일 경우 예외 발생")
    void throwExceptionByNull() {
        assertThatThrownBy(() -> new Card(null, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("null");
    }
}
