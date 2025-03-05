package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardTest {

    @Test
    @DisplayName("카드는 숫자와 모양을 가진다.")
    void card() {
        // given
        Card card = new Card(CardNumber.EIGHT, CardShape.CLOVER);

        // when & than
        assertAll(
                () -> assertThat(card.getNumber()).isEqualTo(8),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.CLOVER)
        );
    }
}
