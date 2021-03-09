package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {
    @Test
    @DisplayName("생성된 카드 Score 테스트")
    void checkValue() {
        Card card = new Card(CardNumber.TWO, Shape.DIAMOND);
        assertThat(card.getScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("에이스 확인 테스트")
    void isAceTest() {
        assertTrue(new Card(CardNumber.ACE, Shape.CLOVER).isAce());
        assertFalse(new Card(CardNumber.TWO, Shape.CLOVER).isAce());
    }
}