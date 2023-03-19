package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class CardTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        assertThatNoException()
                .isThrownBy(() -> new Card(CardShape.SPADE, CardNumber.TWO));
    }
}
