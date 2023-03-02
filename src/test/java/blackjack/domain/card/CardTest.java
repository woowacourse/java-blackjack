package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드의 숫자를 보고 값을 반환한다")
    void get_value() {
        Card card = new Card(Shape.SPADE, Number.FOUR);
        assertThat(card.getPoint()).isEqualTo(4);
    }
}
