package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("동등성을 정확하게 판단한다.")
    void equalsTest() {
        // given
        Card card = Card.of(Shape.SPADE, Number.ACE);
        // when, then
        assertThat(card)
                .isEqualTo(Card.of(Shape.SPADE, Number.ACE))
                .isNotEqualTo(Card.of(Shape.HEART, Number.ACE));
    }
}
