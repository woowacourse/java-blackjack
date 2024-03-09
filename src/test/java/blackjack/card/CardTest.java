package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드의 점수를 정확하게 계산한다.")
    void calculateCardTest() {
        Card card = new Card(Shape.SPADE, Number.ACE);
        assertThat(card.getScore()).isEqualTo(1);
    }

    @Test
    @DisplayName("동등성을 정확하게 판단한다.")
    void equalsTest() {
        // given
        Card card = new Card(Shape.SPADE, Number.ACE);
        // when, then
        assertThat(card)
                .isEqualTo(new Card(Shape.SPADE, Number.ACE))
                .isNotEqualTo(new Card(Shape.HEART, Number.ACE));
    }
}
