package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("에이스 존재 테스트")
    void containAce() {
        Card card = Card.valueOf(CardPattern.CLOVER, CardNumber.ACE);
        assertTrue(card.isAce());
    }

}
