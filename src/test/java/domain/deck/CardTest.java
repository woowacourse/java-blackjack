package domain.deck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {

    @DisplayName("Suit과 Rank가 똑같은 카드는 두 번 생성되지 않는다.")
    @Test
    void cardOfTest() {
        Card card1 = Card.of(Suit.DIAMOND, Rank.ACE);
        Card card2 = Card.of(Suit.DIAMOND, Rank.ACE);
        assertTrue(card1 == card2);
    }
}
