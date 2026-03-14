package domain;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Deck;
import org.junit.jupiter.api.Test;

class DeckTest {
    private final static Integer DECK_SIZE = 52;

    @Test
    void 카드를_52장_초과해_뽑으면_예외가_발생한다() {
        Deck deck = new Deck();
        for (int i = 0; i < DECK_SIZE; i++) {
            assertDoesNotThrow(deck::drawCard);
        }
        assertThrows(IllegalArgumentException.class, () -> {
            deck.drawCard();
        });
    }
}
