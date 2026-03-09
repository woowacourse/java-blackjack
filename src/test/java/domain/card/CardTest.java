package domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("Ace 카드이면 true를 반환한다.")
    void returnsTrue_whenCardIsAce() {
        // given
        Card card = new Card(Rank.ACE, Suit.CLOVER);

        // when - then
        Assertions.assertTrue(card.isAce());
    }

    @Test
    @DisplayName("Ace 카드가 아니면 false를 반환한다.")
    void returnsFalse_whenCardIsNotAce() {
        // given
        Card card = new Card(Rank.JACK, Suit.DIAMOND);

        // when - then
        Assertions.assertFalse(card.isAce());
    }
}
