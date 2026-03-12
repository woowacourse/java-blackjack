package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    private final static Integer DECK_SIZE = 52;

    @Test
    @DisplayName("덱에서는 52장까지 뽑을 수 있고, 53번째에는 예외가 발생한다.")
    void drawCard_After52Draws_ThrowsException() {
        Deck deck = new Deck();
        for (int i = 0; i < DECK_SIZE; i++) {
            assertDoesNotThrow(deck::drawCard);
        }
        assertThrows(IllegalArgumentException.class, () -> {
            deck.drawCard();
        });
    }
}
