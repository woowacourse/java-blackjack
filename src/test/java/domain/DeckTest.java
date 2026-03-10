package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.card.Card;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 덱을_정상적으로_생성하는_경우() {
        // given
        Deck deck = new Deck();

        // when, then
        assertEquals(52, deck.getCards().size());
    }

    @Test
    void 드로우가_정상적으로_실행되는_경우() {
        // given
        Deck deck = new Deck();

        // when
        Card card = deck.drawCard();

        // then
        assertEquals(51, deck.getCards().size());
        assertFalse(deck.getCards().contains(card));
    }

    @Test
    void 드로우가_실패하는_경우() {
        // given
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        // when, then
        assertThrows(IllegalStateException.class,
                () -> deck.drawCard());
    }
}
