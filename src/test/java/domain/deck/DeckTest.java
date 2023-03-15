package domain.deck;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckTest {

    Deck deck = new Deck();

    @DisplayName("덱의 최대 카드 반환 가능 수는 52번이다.")
    @Test
    void generateCardsTest() {
        final int expectedDeckSize = 52;

        for (int i = 0; i < expectedDeckSize; i++) {
            deck.popCard();
        }
        assertThrows(NoSuchElementException.class, deck::popCard);
    }
}
