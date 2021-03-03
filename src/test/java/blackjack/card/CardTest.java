package blackjack.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @Test
    @DisplayName("생성된 카드 value 테스트")
    void checkValue() {
        Card card = new Card(Number.TWO, Shape.DIAMOND);
        assertThat(card.getValue()).isEqualTo(2);
    }
}