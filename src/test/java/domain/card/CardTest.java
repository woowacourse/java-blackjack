package domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드는 랭크 점수를 반환한다")
    void cardScore() {
        Card card = new Card(Suit.HEART, Rank.KING);

        assertEquals(10, card.getScore());
        assertEquals(Suit.HEART, card.getSuit());
        assertEquals(Rank.KING, card.getRank());
    }

    @Test
    @DisplayName("A 카드는 isAce가 true다")
    void aceCard() {
        Card card = new Card(Suit.SPADE, Rank.ACE);

        assertTrue(card.isAce());
    }

    @Test
    @DisplayName("카드는 suit 혹은 rank가 null이면 예외가 발생한다")
    void nullGuard() {
        assertThrows(NullPointerException.class, () -> new Card(null, Rank.ACE));
        assertThrows(NullPointerException.class, () -> new Card(Suit.SPADE, null));
    }
}
