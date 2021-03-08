package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {
    @Test
    @DisplayName("숫자 계산 테스트")
    void addNumber() {
        Card card = new Card(CardPattern.SPADE, CardNumber.SEVEN);
        assertEquals(10, card.addPoint(3));
    }

    @Test
    @DisplayName("에이스 존재 테스트")
    void containAce() {
        Card card = new Card(CardPattern.CLOVER, CardNumber.ACE);
        assertTrue(card.isAce());
    }
}
