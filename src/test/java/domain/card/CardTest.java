package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("Symbol과 Denomination이 동일하면 같은 카드로 인식한다.")
    void testEquals() {
        Card card1 = Card.of(Symbol.HEART, Denomination.EIGHT);
        Card card2 = Card.of(Symbol.HEART, Denomination.EIGHT);

        assertThat(card1).isEqualTo(card2);
    }

    @Test
    @DisplayName("Symbol과 Denomination 중 하나라도 다르면 다른 카드로 인식한다.")
    void testNotEquals() {
        Card card1 = Card.of(Symbol.HEART, Denomination.EIGHT);
        Card card2 = Card.of(Symbol.HEART, Denomination.SEVEN);

        assertThat(card1).isNotEqualTo(card2);
    }
}