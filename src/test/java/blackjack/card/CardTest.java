package blackjack.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {
    @Test
    @DisplayName("생성된 카드 value 테스트")
    void checkValue() {
        Card card = new Card(Number.TWO, Shape.DIAMOND);
        assertThat(card.getValue()).isEqualTo(2);
    }

    @Test
    void isAceTest() {
        assertTrue(new Card(Number.ACE, Shape.CLOVER).isAce());
        assertFalse(new Card(Number.TWO, Shape.CLOVER).isAce());
    }
}