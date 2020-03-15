package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CardTest {

    @DisplayName("싱글톤 카드 동일성 확인")
    @Test
    void equalsTest() {
        Card card = Card.of(Type.TWO, Figure.HEART);
        Card expected = Card.of(Type.TWO, Figure.HEART);
        assertThat(card).isEqualTo(expected);
    }
}
